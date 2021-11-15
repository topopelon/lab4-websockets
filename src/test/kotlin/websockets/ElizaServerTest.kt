package websockets

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*
import org.springframework.boot.web.server.LocalServerPort
import java.net.URI
import java.util.concurrent.CountDownLatch
import javax.websocket.*


@SpringBootTest(webEnvironment = RANDOM_PORT)
class ElizaServerTest {

    private lateinit var container: WebSocketContainer

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        container = ContainerProvider.getWebSocketContainer()
    }

    @Test
    fun onOpen() {
        // Like wait(3), expecting 3 signals from other threads
        val latch = CountDownLatch(3)
        val list = mutableListOf<String>()
        // Start a websocket client
        val client = ElizaOnOpenMessageHandler(list, latch)
        // Start a websocket connection with the server
        container.connectToServer(client, URI("ws://localhost:$port/eliza"))
        latch.await()
        assertEquals(3, list.size)
        assertEquals("The doctor is in.", list[0])
    }

    @Test
    fun onChat() {
        val latch = CountDownLatch(4)
        val list = mutableListOf<String>()

        val client = ElizaOnOpenMessageHandlerToComplete(list, latch)
        container.connectToServer(client, URI("ws://localhost:$port/eliza"))
        latch.await()
        // [list.size] will be 4 or 5 depending of pc speed, showing also `---`
        assertTrue(list.size in (4..5))
        assertEquals("We were discussing you, not me.", list[3])
    }

}

@ClientEndpoint
class ElizaOnOpenMessageHandler(
    private val list: MutableList<String>,
    private val latch: CountDownLatch
) {
    @OnMessage
    fun onMessage(message: String) {
        list.add(message)
        latch.countDown()
    }
}

@ClientEndpoint
class ElizaOnOpenMessageHandlerToComplete(
    private val list: MutableList<String>,
    private val latch: CountDownLatch
) {

    @OnMessage
    fun onMessage(message: String, session: Session) {
        // Store received message from server
        list.add(message)
        // Like signal(), signals [latch] wait once
        latch.countDown()
        // When last signal()
        if (latch.count == 1L) {
            // Send the server a message
            session.basicRemote.sendText("you")
        }
    }
}
