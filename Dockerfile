FROM azul/zulu-openjdk:17
WORKDIR /spring
COPY ./build/libs/*.jar ./server.jar
RUN apt-get update && apt-get install -y python3 python3-pip
RUN pip3 install transformers
ENTRYPOINT ["java", "-jar", "server.jar","--debug"]
