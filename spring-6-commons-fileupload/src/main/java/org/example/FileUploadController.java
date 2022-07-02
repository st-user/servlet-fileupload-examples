package org.example;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FileUploadController {

    @PostMapping("/streaming")
    public View streamingUpload(HttpServletRequest req) throws IOException, FileUploadException {

        if (!ServletFileUpload.isMultipartContent(req)) {
            throw new UnsupportedOperationException("Requests must contain multipart content.");
        }

        ServletFileUpload upload = new ServletFileUpload();
        upload.setSizeMax(-1);
        upload.setFileSizeMax(-1);

        FileItemIterator iter = upload.getItemIterator(req);
        while (iter.hasNext()) {

            FileItemStream item = iter.next();
            try (InputStream is = item.openStream()) {
                byte[] buf = new byte[2048];
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
