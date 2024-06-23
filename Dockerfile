FROM eclipse-temurin:17
WORKDIR /
ADD build/libs/sf2-0.0.1-SNAPSHOT.jar sf2.jar
EXPOSE 8090
CMD java -jar sf2.jar

