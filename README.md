# servlet-fileupload-examples

Example projects showing the way of uploading files by using [Commons FileUpload](https://commons.apache.org/proper/commons-fileupload/index.html), servlet and [Spring Boot](https://spring.io/projects/spring-boot).

## Projects

### [simple-servlet-file-upload](./simple-servlet-file-upload/)
 
Simple servlet web application using Commons FileUpload and servlet standard file upload components. One of its endpoints utilizes [Commons FileUpload Streaming API](https://commons.apache.org/proper/commons-fileupload/streaming.html).

This project uses jakarta EE 8.

### [spring-commons-fileupload](./spring-commons-fileupload/)
 
Spring Boot application using Commons FileUpload. This application also utilizes Streaming API.

This project uses Spring Boot version 2(Spring framework version 5).

### [spring-standard-fileupload](./spring-standard-fileupload/)
 
Spring Boot application using servlet standard file upload components.

This project uses Spring Boot version 2(Spring framework version 5).

### [spring-commons-fileupload-with-spring-security](./spring-commons-fileupload-with-spring-security/)

Spring Boot application using Commons FileUpload with Spring Security. 

In this application, we send a multipart HTTP request including a **CSRF token**.
The CSRF token is included in URL. We have to prevent the Spring's multipart resolver from parsing the HTTP request body so that we have to make it possible for Commons FileUpload Stream API to parse it.



### [spring-6-commons-fileupload](./spring-6-commons-fileupload/)
 
Spring Boot application using Commons FileUpload Streaming API. This project uses Spring Boot **version 3** (which depends on Spring framework **version 6**). From version 6, Spring requires jakarta EE 9+. This means that it requires `jakarta.*` namespace.

Even the latest version of Commons FileUpload isn't integrated with `jakarta.*` namespace. It still depends on old `javax.*` namespace.

So Spring no longer supports the components for Commons FileUpload(e.g. CommonsMultipartResolver. Related issue: [Drop outdated Servlet-based integrations: Commons FileUpload, FreeMarker JSP support, Tiles](https://github.com/spring-projects/spring-framework/issues/27423)).

This project can become a rescue for someone who wants to keep using Commons FileUpload with Spring framework.

In this project, we use [Eclipse Transformer project](https://github.com/eclipse/transformer) in order to convert from `javax.*` namespace to `jakarta.*` namespace in Commons FileUpload jar and we directly call Commons FileUpload API(Streaming API) in the controller's methods in the Spring Boot application.

## MISC

### Logging

The applications in this project output logs about changes on the temporary directory(`javax.servlet.context.tempdir`) by
utilizing `WatchService` API.

If we don't use [Commons FileUpload Stream API](https://commons.apache.org/proper/commons-fileupload/streaming.html),
uploaded files are written to the directory if the files are considered 'large' files. By default, files larger than
1KB are considered 'Large' files in this project.

**reference**:

- [A Guide to WatchService in Java NIO2 - Baeldung](https://www.baeldung.com/java-nio2-watchservice)


### Create large files for testing

``` bash

dd if=/dev/zero of=1g.img bs=1 count=0 seek=1G

```

### Output GC logs for Spring Boot applications

``` bash

./mvnw clean package
java -Xlog:gc:${HOME}/work/gc.log -jar target/${APPLICATION_JAR_NAME}.jar


```


**reference:**

 - [Linux / UNIX: Create Large 1GB Binary Image File With dd Command](https://www.cyberciti.biz/faq/howto-create-lage-files-with-dd-command/)



