FROM eclipse-temurin:19-jdk-alpine

ENV CATALINA_HOME=/opt/tomcat \
    TOMCAT_MAJOR="10" \
    TOMCAT_VERSION="10.1.4"

RUN mkdir $CATALINA_HOME

WORKDIR $CATALINA_HOME/tomcat
RUN wget -O apache-tomcat.tar.gz http://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_MAJOR}/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz
RUN tar xvfz apache*.tar.gz
RUN mv apache-tomcat-${TOMCAT_VERSION}/* $CATALINA_HOME/.

RUN rm -rf \
  ${CATALINA_HOME}/webapps/ROOT \
  ${CATALINA_HOME}/webapps/docs \
  ${CATALINA_HOME}/webapps/examples

ENV JAVA_OPTS="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n -Djava.compiler=NONE -Djava.awt.headless=true"

COPY court.war $CATALINA_HOME/webapps/

EXPOSE 8080 5005 9000

ENTRYPOINT exec ${CATALINA_HOME}/bin/catalina.sh run
