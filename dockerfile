FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/envio-email.jar envio-email.jar
ENTRYPOINT ["java","-jar","/envio-email.jar"]

ENV GMAIL_USERNAME=your_gmail_username
ENV GMAIL_PASSWORD=your_gmail_password