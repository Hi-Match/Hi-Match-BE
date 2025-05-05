FROM azul/zulu-openjdk:17
WORKDIR /spring
COPY ./build/libs/*.jar ./server.jar
RUN apt-get update && apt-get install -y python3 python3-pip
ENTRYPOINT ["java", "-jar", "server.jar","--debug"]
