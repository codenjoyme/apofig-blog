FROM openjdk:11
ARG CONTEXT=blog
WORKDIR /usr/src/app
COPY target/*.jar ${CONTEXT}.jar