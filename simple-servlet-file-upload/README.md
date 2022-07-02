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
- [Deploying a Java War in a Docker Container](https://www.baeldung.com/docker-deploy-java-war)