FROM openjdk:17-oracle

MAINTAINER Matias_Rogante

COPY target/BackEndCRUD-0.0.1-SNAPSHOT.jar BackEndCRUD-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/BackEndCRUD-0.0.1-SNAPSHOT.jar"]