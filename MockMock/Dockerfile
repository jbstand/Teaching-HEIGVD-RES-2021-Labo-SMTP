FROM openjdk:11
MAINTAINER Jeremie Melly <jeremie.melly@heig-vd.ch>

#Install NetCat to test server container
RUN apt-get update && \
    apt-get -y install netcat && \
    apt-get clean

# Copy the MockMock JAR file in /opt
COPY ./release/MockMock.jar /opt/

# Our application will accept TCP connections on port 8888.
EXPOSE 25
EXPOSE 8282

# Command executed at startup
CMD ["java", "-jar", "/opt/MockMock.jar", "-p 25"]