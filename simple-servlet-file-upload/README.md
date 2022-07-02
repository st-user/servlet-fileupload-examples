# simple-servlet-file-upload

Simple servlet web application using Commons FileUpload and servlet standard file upload components. One of its
endpoints
utilizes [Commons FileUpload Streaming API](https://commons.apache.org/proper/commons-fileupload/streaming.html)

## Build & Run

### Jetty Plugin

``` bash
./mvnw clean compile
./mvnw jetty:run
```

Open a browser and access `http://localhost:8080`.

### Tomcat on Docker

``` bash
./mvnw clean package
docker build -t my-tomcat .
docker run --rm -it -d -p 8080:8080 --name my-tomcat my-tomcat
```

Open a browser and access `http://localhost:8080/App`.

**reference:**

- [Apache Maven WAR Plugin](https://maven.apache.org/plugins/maven-war-plugin/index.html)
- [Deploying a Java War in a Docker Container - Baeldung](https://www.baeldung.com/docker-deploy-java-war)

## Logging

This application outputs logs about changes on the temporary directory(`javax.servlet.context.tempdir`) by
utilizing `WatchService` API.

If we don't use [Commons FileUpload Stream API](https://commons.apache.org/proper/commons-fileupload/streaming.html),
uploaded files are written to the directory if the files are considered 'large' files. By default, files larger than
1KB are considered 'Large' files in this application.

**reference**:

- [A Guide to WatchService in Java NIO2 - Baeldung](https://www.baeldung.com/java-nio2-watchservice)

## References

- [Uploading Files with Servlets and JSP - Baeldung](https://www.baeldung.com/upload-file-servlet)
