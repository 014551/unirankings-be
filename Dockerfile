FROM ubuntu:20.04
ENV DEBIAN_FRONTEND=noninteractive
ARG JAR_FILE=target/*.jar
RUN apt-get update
RUN apt-get -y install openjdk-17-jdk openjdk-17-jre
RUN apt-get install -y wget
RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN apt-get -y install ./google-chrome-stable_current_amd64.deb
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8443