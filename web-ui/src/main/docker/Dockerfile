FROM gcr.io/google-appengine/openjdk
VOLUME /tmp
ADD stockranking.jar app.jar
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
