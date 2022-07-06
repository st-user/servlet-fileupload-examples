# spring-commons-fileupload-with-spring-security

Spring Boot application using Commons FileUpload with Spring Security.

In this application, we send a multipart HTTP request including a **CSRF token**.
The CSRF token is included in URL. We have to prevent the Spring's multipart resolver from parsing the HTTP request body
so that we have to make it possible for Commons FileUpload Streaming API to parse it.

## Build & Run

``` bash
./mvnw clean spring-boot:run
```

Open a browser and access `http://localhost:8080/upload`.
When you visit the page, you are redirected to the login page.

### How to log in?

When the application successfully starts,
a password for development is generated and output to the console.

Example:

```
Using generated security password: a6cf4682-3e5c-433b-9d93-e3ffbc1691fc

This generated password is for development use only. Your security configuration must be updated before running your application in production.
```

You can log in to the application by using a username of `user` and the password.

## References

- [Multipart (file upload) - Spring Security Cross Site Request Forgery (CSRF) for Servlet Environments](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#servlet-csrf-considerations-multipart)