FROM openjdk:8

MAINTAINER zhouzhou
LABEL name="pagehelper-demo" version="1.0" author="zhouzhou"

ENV SERVICE_NAME pagehelper

RUN mkdir -p /data/logs
ADD ${SERVICE_NAME}.jar ${SERVICE_NAME}-image.jar
ENTRYPOINT java -jar ${SERVICE_NAME}-image.jar

