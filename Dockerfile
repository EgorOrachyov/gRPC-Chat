FROM java:8-jdk-alpine
COPY ./build /usr/app
WORKDIR /usr/app

CMD java -jar libs/gRPC-Chat.jar