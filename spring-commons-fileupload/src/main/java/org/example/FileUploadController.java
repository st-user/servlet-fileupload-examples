package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FileUploadController {
    @Autowired
    private Config config;

    private static final int BUFFER_SIZE = 2048;

    @PostMapping("/normal")
    public View upload(@RequestParam List<CommonsMultipartFile> uploadFile,
                       @RequestParam String textData) throws IOException {

        for (CommonsMultipartFile item : uploadFile) {

            byte[] buf = new byte[BUFFER_SIZE];
            long totalBytes = 0;
            try (InputStream is = item.getInputStream()) {
                int n;
                while ((n = is.read(buf)) != -1) {
                    totalBytes += n;
                }
            }
            System.out.printf("The size of %s is %d bytes.%n", item.getOriginalFilename(),
                totalBytes);

        }
        System.out.printf("textData(%s) is a simple form field.%n", textData);

        return new RedirectView("/index.html");
    }

    @PostMapping("/streaming")
    public View streamingUpload(HttpServletRequest req) throws IOException, FileUploadException {

        if (!ServletFileUpload.isMultipartContent(req)) {
            throw new UnsupportedOperationException("Requests must contain multipart content.");
        }

        ServletFileUpload upload = new ServletFileUpload();
        upload.setSizeMax(config.maxRequestSize);
        upload.setFileSizeMax(config.maxFileSize);

        FileItemIterator iter = upload.getItemIterator(req);
        while (iter.hasNext()) {

            FileItemStream item = iter.next();
            try (InputStream is = item.openStream()) {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                long totalBytes = 0;
                while ((n = is.read(buf)) != -1) {
                    totalBytes += n;
                }

                if (item.isFormField()) {
                    System.out.printf("%s(%s) is a simple form field.%n", item.getFieldName(),
                        new String(buf, 0, (int) totalBytes, StandardCharsets.UTF_8));
                } else {
                    System.out.printf("The size of %s is %d bytes.%n", item.getName(),
                        totalBytes);
                }

            }
        }

        return new RedirectView("/index.html");
    }
}
