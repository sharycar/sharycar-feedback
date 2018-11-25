FROM openjdk:8-jre-alpine
RUN mkdir /app
WORKDIR /app
ADD ./feedback-api/target/sharycar-feedback-api-1.0.0.jar /app
EXPOSE 8082
CMD ["java", "-jar", "sharycar-feedback-api-1.0.0.jar"]
