# dockerfile 基础配置
FROM daocloud.io/library/java:8u40-b22
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE ${EXPOSE}
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]