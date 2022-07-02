package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Executors;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class MonitoringFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

        File repository =
            (File) config.getServletContext().getAttribute("javax.servlet.context.tempdir");
        Executors.newSingleThreadExecutor().submit(() -> {

            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path path = repository.toPath();

                path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY
                );

                WatchKey key;
                while ((key = watchService.take()) != null) {

                    for (WatchEvent<?> event : key.pollEvents()) {
                        System.out.printf("[TEMP_DIR]<%s> %s.%n", event.kind(), event.context());
                    }
                    key.reset();
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        long start = System.currentTimeMillis();

        chain.doFilter(request, response);

        String path = ((HttpServletRequest) request).getServletPath();
        System.out.printf("%s takes %d ms.%n", path, System.currentTimeMillis() - start);
    }

    @Override
    public void destroy() {
    }
}
