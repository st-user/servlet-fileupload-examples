# spring-6-commons-fileupload

Spring Boot application using Commons FileUpload Streaming API. This project uses Spring Boot **version 3** (which
depends on Spring framework **version 6**).

For the information of the problem about Commons FileUpload used with Spring framework 6, Please
see [this README](../README.md#spring-6-commons-fileupload).

## Build & Run

### Convert 'javax.*' namespace

First, we have to convert `javax.*` namespace of the Commons FileUpload jar file.

To do this, we utilize [Eclipse Transformer project](https://github.com/eclipse/transformer).

#### Download the binary

We can download the binary from [Sonatype repository or Maven Central repository](https://projects.eclipse.org/projects/technology.transformer/downloads).


#### unzip the binary

``` bash
unzip org.eclipse.transformer.cli-0.4.0-distribution.jar
```

#### Run conversion

Prerequisite: You have to download the Commons FileUpload jar to your maven local repository **before** you run the following commands

``` bash

PATH_TO_YOUR_MAVEN_LOCAL_REPO=
# e.g. PATH_TO_YOUR_MAVEN_LOCAL_REPO=${HOME}/.m2/repository 

java -jar org.eclipse.transformer.cli-0.4.0.jar \
  ${PATH_TO_YOUR_MAVEN_LOCAL_REPO}/commons-fileupload/commons-fileupload/1.4/commons-fileupload-1.4.jar \
  ${PATH_TO_YOUR_MAVEN_LOCAL_REPO}/commons-fileupload/commons-fileupload/1.4/commons-fileupload-1.4-jakarta.jar
  
```

**reference:**

- [Enabling Jakarta EE 9 for development and test by using the Eclipse Transformer](https://openliberty.io/blog/2021/03/17/eclipse-transformer.html)
- [How to Use Eclipse Transformer to Convert a 3rd Party Library to the New Jakarta Namespace](https://blog.payara.fish/how-to-use-the-eclipse-transformer-project-to-convert-a-3rd-party-library)

### Run the application

``` bash
./mvnw clean spring-boot:run 
```
