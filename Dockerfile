FROM eclipse-temurin:17
WORKDIR /
ADD build/libs/sf2-0.0.1-SNAPSHOT.jar sf2.jar
RUN wget https://github.com/grafana/grafana-opentelemetry-java/releases/latest/download/grafana-opentelemetry-java.jar
EXPOSE 8090
CMD ["java", "-javaagent:/grafana-opentelemetry-java.jar", "-jar", "sf2.jar"]
