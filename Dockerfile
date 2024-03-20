FROM openjdk

WORKDIR /app

COPY target/*.jar /app/music-player.jar

ENTRYPOINT ["java","-jar","music-player.jar"]