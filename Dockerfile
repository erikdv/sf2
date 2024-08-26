FROM eclipse-temurin:17
WORKDIR /
ADD build/libs/sf2-0.0.1-SNAPSHOT.jar sf2.jar
ADD opentelemetry-javaagent.jar opentelemetry-javaagent.jar
EXPOSE 8090
CMD java -javaagent:/opentelemetry-javaagent.jar \
  -Dotel.metrics.exporter=none \
  -Dotel.logs.exporter=none \
  -Dotel.exporter.otlp.protocol=http/protobuf \
  -Dotel.service.name=sf2-backend \
  -jar sf2.jar

