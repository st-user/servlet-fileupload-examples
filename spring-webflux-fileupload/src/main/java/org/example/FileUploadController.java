package org.example;

import java.util.Collection;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.reactive.result.view.View;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class FileUploadController {

    @PostMapping("/upload")
    public Mono<View> upload(@RequestBody Mono<MultiValueMap<String, Part>> parts) {
        return parts.flatMap(m -> {
            return Flux.fromStream(m.values().stream().flatMap(Collection::stream))
                .flatMap(part -> part.content().map(buf -> {
                        int cnt = buf.readableByteCount();
                        DataBufferUtils.release(buf);
                        return cnt;
                    })
                    .reduce(Integer::sum))
                .map(byteCount -> {
                    System.out.println("total bytes : " + byteCount);
                    return 1;
                }).reduce(Integer::sum)
                .map(successCount -> {
                    System.out.println("Success count : " + successCount);
                    return new RedirectView("/index.html");
                });
        });
    }
}
