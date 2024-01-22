#mvn --version
FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests  

#java --version
FROM  openjdk:19
VOLUME /tmp
EXPOSE 80
COPY --from=build /home/app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
