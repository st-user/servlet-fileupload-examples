# servlet-fileupload-examples

Example projects showing the way of uploading files by using [Commons FileUpload](https://commons.apache.org/proper/commons-fileupload/index.html), servlet and [Spring Boot](https://spring.io/projects/spring-boot).

## Projects

### [simple-servlet-file-upload](./simple-servlet-file-upload/)
 
Simple servlet web application using Commons FileUpload and servlet standard file upload components. One of its endpoints utilizes [Commons FileUpload Streaming API](https://commons.apache.org/proper/commons-fileupload/streaming.html)

This project uses jakarta EE 8.

### [spring-commons-fileupload](./spring-commons-fileupload/)
 
Spring Boot application using Commons FileUpload. This application also utilizes Streaming API.

This project uses Spring Boot version 2(Spring framework version 5).

### [spring-standard-fileupload](./spring-standard-fileupload/)
 
Spring Boot application using servlet standard file upload components.

This project uses Spring Boot version 2(Spring framework version 5).

### [spring-6-commons-fileupload](./spring-6-commons-fileupload/)
 
Spring Boot application using Commons FileUpload Streaming API. This project uses Spring Boot **version 3** (which depends on Spring framework **version 6**). From version 6, Spring requires jakarta EE 9+. This means that it requires `jakarta.*` namespace.

Even the latest version of Commons FileUpload isn't integrated with `jakarta.*` namespace. It still depends on old `javax.*` namespace.

So Spring no longer supports the components for Commons FileUpload(e.g. CommonsMultipartResolver. Related issue: [Drop outdated Servlet-based integrations: Commons FileUpload, FreeMarker JSP support, Tiles](https://github.com/spring-projects/spring-framework/issues/27423)).

This project can become a rescue for someone who wants to keep using Commons FileUpload with Spring framework.

In this project, we uses [Eclipse Transformer project](https://github.com/eclipse/transformer) in order to convert from `javax.*` namespace to `jakarta.*` namespace in Commons FileUpload jar and in the Spring Boot application, we directly calls Commons FileUpload API(Streaming API) in the controller's methods.

## MISC

### Create large files for testing

```

dd if=/dev/zero of=1g.img bs=1 count=0 seek=1G

```

**reference:**

[Linux / UNIX: Create Large 1GB Binary Image File With dd Command](https://www.cyberciti.biz/faq/howto-create-lage-files-with-dd-command/)



