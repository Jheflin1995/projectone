
FROM tomcat:8.0-jre8


LABEL maintainer="Joe"

ADD target/employee-servlet-app.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["catalina.sh","run"]