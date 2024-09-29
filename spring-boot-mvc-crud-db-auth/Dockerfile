FROM tomcat:9.0.95-jdk8-temurin-noble

EXPOSE 8080

RUN rm -rf ./webapps/*
COPY target/app.war /usr/local/tomcat/webapps/app.war
ENV JAVA_OPTS="-Dspring.profiles.active=dev"
CMD ["catalina.sh", "run"]
