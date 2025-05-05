FROM azul/zulu-openjdk:17
WORKDIR /spring
COPY ./build/libs/*.jar ./server.jar
RUN apt-get update && apt-get install -y python3 python3-pip
RUN pip3 install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cpu  # CPU 버전 명시
RUN pip3 install transformers==4.35.2
RUN python3 -c "from transformers import AutoTokenizer, AutoModel; AutoModel.from_pretrained('jhgan/ko-sroberta-multitas'); AutoTokenizer.from_pretrained('jhgan/ko-sroberta-multitas')"
ENTRYPOINT ["java", "-jar", "server.jar","--debug"]
