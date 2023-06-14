FROM amazoncorretto:17

MAINTAINER Matias_Rogante

COPY target/BackEndCRUD-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

