package org.example;

import static org.example.Constants.BUFFER_SIZE;
import static org.example.Constants.FILE_SIZE_THRESHOLD;
import static org.example.Constants.MAX_FILE_SIZE;
import static org.example.Constants.MAX_REQUEST_SIZE;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/standard")
@MultipartConfig(
    fileSizeThreshold = FILE_SIZE_THRESHOLD,
    maxFileSize = MAX_FILE_SIZE,
    maxRequestSize = MAX_REQUEST_SIZE
)
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        for (Part part : req.getParts()) {

            try (InputStream is = part.getInputStream()) {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                long totalBytes = 0;
                while ((n = is.read(buf)) != -1) {
                    totalBytes += n;
                }
                if (part.getSubmittedFileName() == null) {
                    System.out.printf("%s(%s) is a simple form field.%n", part.getName(),
                        new String(buf, 0, (int) totalBytes, StandardCharsets.UTF_8));
                } else {
                    System.out.printf("The size of %s is %d bytes.%n", part.getSubmittedFileName(),
                        totalBytes);
                }
            }
        }

        resp.sendRedirect("/");
    }
}
