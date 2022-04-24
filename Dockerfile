FROM openjdk:11-jre-slim

# TODO: do the magic!

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar","/postgis-example.jar"]