FROM adoptopenjdk/openjdk14:latest
VOLUME /tmp
ADD /target/my-server.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
