package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import static org.example.Constants.*;

@WebServlet("/commons-streaming")
public class CommonsStreamingFileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(req)) {
            throw new UnsupportedOperationException("Requests must contain multipart content.");
        }

        ServletFileUpload upload = new ServletFileUpload();
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setFileSizeMax(MAX_FILE_SIZE);

        try {
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
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
