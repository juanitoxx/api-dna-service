FROM amazoncorretto:11
#FROM openjdk:11.0.2-jdk-oraclelinux7

LABEL key="mercadolibre"

ADD build/libs /opt/spring-boot
ADD src/main/resources  /opt/config

### Set Environment
ENV SERVER_HOME /opt/spring-boot

WORKDIR /opt/spring-boot

ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

### Open Ports
EXPOSE 8052

### Start instance
ENTRYPOINT ["java", "-jar", "-Dfile.encoding=UTF-8", "/opt/spring-boot/api-dna-service-0.0.1-SNAPSHOT.jar"]