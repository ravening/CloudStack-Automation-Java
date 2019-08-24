FROM openjdk:11
COPY ./target/cloudstack-automation-0.0.1.jar /var/tmp
WORKDIR /var/tmp
RUN sh -c 'touch cloudstack-automation-0.0.1.jar'
ENTRYPOINT ["java","-jar","cloudstack-automation-0.0.1.jar"]
