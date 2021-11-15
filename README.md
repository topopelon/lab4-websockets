# Web Engineering 2020-2021 / Websockets
**In this assignment your PR must only modify the `README.md` file**.
Please, go to the [Wiki](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets/wiki) in order to get the instructions for this assignment.

## Historical background

The code implements the script DOCTOR of the [ELIZA](https://en.wikipedia.org/wiki/ELIZA) system and provides you a client and server that communicate each other by means of websockets.
ELIZA is an early example of primitive natural language processing developed by [Joseph Weizenbaum](https://en.wikipedia.org/wiki/Joseph_Weizenbaum) between 1964 and 1966.
ELIZA operated by processing users' responses to scripts, the most famous of which was DOCTOR, a simulation of a [Rogerian psychotherapist](https://en.wikipedia.org/wiki/Person-centered_therapy).
Using almost no information about human thought or emotion, DOCTOR sometimes provided a startlingly human-like interaction.

## Primary goal

The objective is to complete `ElizaServerTest.kt` by completing the test `onChat` that now is ignored.
The `onChat` test must:

* Send a message from the client to the server that is running a Java implementation of the script DOCTOR.
* Check the client receives a DOCTOR question about your mental health (see examples in `Eliza.kt`).

You must remove `@Disabled` and place up to 6 lines of code to fulfil the task.
`ElizaServerTest.kt` includes the test `onOpen` that will provide you insights.

## Secondary goals (:gift:)

Solutions must meet primary goals too.

Proposed:

- Support STOMP in the server side and create a small client that uses it.
- Support SockJS in the server side and show that xhr-polling can be used as transport instead of WebSocket when needed ([additional info](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-fallback-sockjs-client)).
- Turn the DOCTOR into a client of the server, so, the server acts only as message broker.
- Use the server as a relay server to connect to an external message broker (e.g. RabbitMQ, [additional info](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-handle-broker-relay))

Note: unless the goal specifies o disallows a specific framework you are free to replace the framework used in the original implementation with a different framework.

Manifest your intention first by a PR updating this `README.md` with your goal.
If you desist of your goal, release it by a PR so other fellow can try it.

| User name | NIA | CI | Solution | Score |
|--------|-----------|------|--------------|-------------|
|[UNIZAR-30246-WebEngineering](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets) | 30246 | [![Build Status](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets/actions/workflows/ci.yml) |
|        |           |      |              |             |
[Jorge Laguna](https://github.com/topopelon/lab4-websockets/tree/test) | 735550 | [![Build Status](https://github.com/topopelon/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/topopelon/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/topopelon/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
