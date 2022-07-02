package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FileUploadController {

    @PostMapping("/upload")
    public View upload(@RequestParam List<MultipartFile> uploadFile, @RequestParam String textData)
        throws IOException {

        for (MultipartFile item : uploadFile) {

            byte[] buf = new byte[2048];
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
}
