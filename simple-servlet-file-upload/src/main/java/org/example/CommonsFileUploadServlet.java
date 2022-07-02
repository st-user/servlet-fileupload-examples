package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

import static org.example.Constants.*;

@WebServlet("/commons")
public class CommonsFileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(req)) {
            throw new UnsupportedOperationException("Requests must contain multipart content.");
        }

        FileCleaningTracker tracker =
            FileCleanerCleanup.getFileCleaningTracker(req.getServletContext());
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(FILE_SIZE_THRESHOLD);
        factory.setFileCleaningTracker(tracker);

        File repository =
            (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setFileSizeMax(MAX_FILE_SIZE);

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    System.out.printf("%s(%s) is a simple form field.%n", item.getFieldName(),
                        item.getString(req.getCharacterEncoding())); // UTF-8
                } else {
                    try (InputStream is = item.getInputStream()) {
                        byte[] buf = new byte[BUFFER_SIZE];
                        int n;
                        long totalBytes = 0;
                        while ((n = is.read(buf)) != -1) {
                            totalBytes += n;
                        }
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
