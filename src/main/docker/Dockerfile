FROM java:7
MAINTAINER emarineli@gmail.com
VOLUME /tmp
ADD exploracao-marte-1.0-SNAPSHOT.jar exploracao-marte.jar
RUN bash -c 'touch /exploracao-marte.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/exploracao-marte.jar"]
