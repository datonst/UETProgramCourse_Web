# syntax=docker.io/docker/dockerfile:1
# ### azul/zulu-openjdk:20.0.2 as builder
#docker build -t sontran824/uet-program-spring:0.38.5
# docker push sontran824/uet-program-spring:0.38.5
FROM eclipse-temurin:17-jdk-jammy as builder
#FROM maven:3.9.3-eclipse-temurin-17-alpine@sha256:1cbc71cb8e2f594338f4b4cbca897b9f9ed6183e361489f1f7db770d57efe839 AS builder
#MAINTAINER uetprogram_systems
WORKDIR /build


COPY mvnw ./mvnw
COPY .mvn ./.mvn
COPY CommonEntity/.mvn ./CommonEntity/.mvn
COPY CommonEntity/mvnw ./CommonEntity/mvnw
COPY WebParent/.mvn ./WebParent/.mvn
COPY WebParent/mvnw ./WebParent/mvnw
COPY WebParent/WebFrontEnd/.mvn ./WebParent/WebFrontEnd/.mvn
COPY WebParent/WebFrontEnd/mvnw ./WebParent/WebFrontEnd/mvnw
COPY WebParent/WebBackend/.mvn ./WebParent/WebBackend/.mvn
COPY WebParent/WebBackend/mvnw ./WebParent/WebBackend/mvnw

COPY pom.xml ./pom.xml
COPY CommonEntity/pom.xml ./CommonEntity/pom.xml
COPY WebParent/pom.xml ./WebParent/pom.xml
COPY WebParent/WebFrontEnd/pom.xml ./WebParent/WebFrontEnd/pom.xml
COPY WebParent/WebBackend/pom.xml ./WebParent/WebBackend/pom.xml

COPY CommonEntity/src ./CommonEntity/src
COPY WebParent/src ./WebParent/src
COPY WebParent/WebBackend/src ./WebParent/WebBackend/src
COPY WebParent/WebFrontEnd/src ./WebParent/WebFrontEnd/src


#RUN ./mvnw clean
#RUN ./CommonEntity/mvnw  dependency:go-offline
#RUN ./CommonEntity/mvnw install
#RUN ./WebParent/mvnw dependency:go-offline
#RUN ./WebParent/mvnw install
#RUN ./WebParent/WebFrontEnd/mvnw dependency:go-offline
#RUN ./WebParent/WebFrontEnd/mvnw install
#RUN ./WebParent/WebBackend/mvnw dependency:go-offline
#RUN ./WebParent/WebBackend/mvnw  install

#RUN ./mvnw dependency:go-offline



RUN ./mvnw clean install




FROM eclipse-temurin:17-jre-jammy AS layers
WORKDIR layer
ARG JAR_FILE=/build/WebParent/WebBackend/target/WebBackend-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar","application.jar"]
#RUN java -Djarmode=layertools -jar application.jar extract
#










#FROM eclipse-temurin:17-jre-jammy
#WORKDIR /opt/app
#RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
#COPY --from=layers /layer/dependencies/ ./
#COPY --from=layers /layer/spring-boot-loader/ ./
#COPY --from=layers /layer/snapshot-dependencies/ ./
#COPY --from=layers /layer/application/ ./
#USER demo
##HEALTHCHECK --interval=30s --timeout=3s --retries=1 CMD wget -qO- http://localhost:8080/actuator/health/ | grep UP || exit 1
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]















#FROM mysql:latest
#
#MAINTAINER mysql_uetprogram
#
#RUN chown -R mysql:root /var/lib/mysql/
#
#ARG MYSQL_DATABASE
#ARG MYSQL_USER
#ARG MYSQL_PASSWORD
#ARG MYSQL_ROOT_PASSWORD
#
#ENV MYSQL_DATABASE=$MYSQL_DATABASE
#ENV MYSQL_USER=$MYSQL_USER
#ENV MYSQL_PASSWORD=$MYSQL_PASSWORD
#ENV MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD
#
#ADD data.sql /etc/mysql/data.sql
#
#RUN sed -i 's/MYSQL_DATABASE/'$MYSQL_DATABASE'/g' /etc/mysql/data.sql
#RUN cp /etc/mysql/data.sql /docker-entrypoint-initdb.d
#
#EXPOSE 3306