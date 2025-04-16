FROM azul/zulu-openjdk:17
WORKDIR /spring
COPY ./build/libs/*.jar ./server.jar
ENTRYPOINT ["java", "-jar", "server.jar","--debug"]
