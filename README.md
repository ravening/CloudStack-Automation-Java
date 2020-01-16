# CloudStack Automation in Java

A Spring Boot project to automate the frequently used CloudStack operations

## Getting Started

Steps mentioned below will tell you how to automate the common tasks done in CloudStack from UI which could save lot of your time.

### Prerequisites

What things you need to install the software and how to install them

```
Java 8+
Apache Maven
Any IDE to import the project
Angular CLI
```

### Installing

A step by step series of instructions that tell you how to start the project

Download the project from github

```
git clone <repo url>
```

Import the project into any IDE as a Maven project.

To talk to CloudStack, you need the URL endpoint, ApiKey and SecretKey.
You need to enter these values in "application.properties" file.

It can connect to multiple CLoudStack endpoints simultaneously.
If you have 5 data centers with CloudStack installed
in different regions then you need to enter the above
mentioned three parameters corresponding to the location
name.

For ex: If you have CloudStack instance running in USA,
EUROPE and ASIA location, below is the configuration
you need to enter


```
usa.url=<https://...>
usa.apiKey=
usa.secretKey=

europe.url=
europe.apiKey=
europe.secretKey=

asia.url=
asia.apiKey=
asia.secretKey=
```

Once this is done, you need to modify the variables in the file

```bash
src/main/java/com.rakeshv.cloudstackautomation/service/CommandBuilderService.java
```

There are three predefined static variables

```bash
private static final String USA = "usa";
private static final String EUROPE = "europe";
private static final String ASIA = "asia";
```

You also need to change the ArrayList containing the platform names.

```bash
static String[] platforms = new String[]{USA, EUROPE, ASIA};
```

Once these changes are done, you are all good to go.
Only steps left is to build the packages and start the project

Make sure that the file "frontend/angular.json" contains the following line

```bash
"outputPath": "../src/main/resources/public",
```

The "outputPath" tells the maven to build frontend packages and put
those templates files into public folder of java project

Install the necessary angular dependencies
```bash
cd frontend
npm install --save-dev @angular-devkit/build-angular
```

Build the project from the top directory using the command

```bash
mvn package
```

This will generate the jar file in the target directory
Now run the project using

```bash
java -jar target/cloudstack-automation-0.0.1.jar
```

Navigate to the following link in your browser

```
http://localhost:8080
```

## Building a docker image

Run the below command to create a docker image

```
./mvnw install dockerfile:build
```

This will create a docker image with name

```
cloudstack-automation
```

Run the docker image using the command

```bash
docker run -p 8080:8080 -t cloudstack-automation
```

## Authors

* **Rakesh Venkatesh** - *Initial work* - [ravening](https://github.com/ravening)
