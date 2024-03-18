FROM openjdk

WORKDIR /app

COPY target/*.jar /app/MusicPlayer.jar

ENTRYPOINT ["java","-jar","MusicPlayer.jar"]

