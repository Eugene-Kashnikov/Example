package core;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import java.io.IOException;

@Slf4j
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String contentString;

        Request request = chain.request();

        long t1 = System.nanoTime();

        log.info("========================= REQUEST =========================");
        log.info("{} {} \n", request.method(), request.url());
        log.info("Headers: \n{}", request.headers());
        if (request.body() != null) {
            Buffer requestBuffer = new Buffer();
            request.body().writeTo(requestBuffer);
            log.info("Body: {}", requestBuffer.readUtf8());
        } else {
            log.info("Without body");
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        log.info("========================= RESPONSE ========================");
        log.info("Response from {}", response.request().url());
        log.info("Headers: \n{}", response.headers());
        MediaType contentType = response.body().contentType();
        try {
            contentString = contentType.toString();
        } catch (NullPointerException e) {
            contentString = "delete";
        }
        if (contentString.contains("json")) {
            String content = response.body().string();
            log.info("Body: \n{}", content);
            log.info("Response time: {} ms", (t2 - t1) / 1e6d);
            log.info("===========================================================");
            ResponseBody wrappedBody = ResponseBody.create(contentType, content);
            return response.newBuilder().body(wrappedBody).build();
        } else {
            log.info("Body: is a file");
            log.info("Response time: {} ms", (t2 - t1) / 1e6d);
            log.info("===========================================================");
            return response;
        }
    }
}