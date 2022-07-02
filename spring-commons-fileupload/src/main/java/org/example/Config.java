package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    @Value("${spring.servlet.multipart.file-size-threshold}") int fileSizeThreshold;
    @Value("${spring.servlet.multipart.max-file-size}") long maxFileSize;
    @Value("${spring.servlet.multipart.max-request-size}") long maxRequestSize;
    @Value("${spring.servlet.multipart.resolve-lazily}") boolean resolveLazily;
}
