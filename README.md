# P2P gRPC based chat

![Image example](https://github.com/EgorOrachyov/gRPC-Chat/blob/master/pictures/ui.png)

Based on Google gRPC framework and protobuf simple chat for local
interaction and message passing. Runs server for root client. 
Supports only single connection to the server (maybe will be fixed later).

Console application, which is located in java.Main, allows to run as server or client for the chat.
Follow the UI instruction in running app to configure your p2p session. 

Example to use from docker
![Docker example](https://github.com/EgorOrachyov/gRPC-Chat/blob/master/pictures/docker.png)

## Instructions

The project uses Java 12 and Gradle as a building tool. An easiest way to run console 
application is import this project into Intellij Idea and run java.Main class.

In order to run app from console execute the following command:  
`./gradlew build && java -jar build/libs/gRPC-Chat.jar`

In order to run docker example execute the following command line instructions:  
`docker run -i -p<port>:<port> char` to run as server  
`docker run -i char` to run as client.

Docker image could be built as follows: `docker build -t chat .`

## Contributors

* Orachyov Egor - [github](https://github.com/EgorOrachyov)
* Osipova Alexandra - [github](https://github.com/FemiLame)
