FROM openjdk:latest
COPY ./target/sem_coursework.jar.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem_coursework.jar", "db", "root", "example"]