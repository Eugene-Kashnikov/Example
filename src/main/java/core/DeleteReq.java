package core;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class DeleteReq extends RestService {

    public ResponsePojo doDelete(String url) {
        String bodyStr = null;
        req = new Request.Builder()
                .url(url)
                .delete()
                .addHeader(AUTH, authValue)
                .build();

        try (Response response = client.newCall(req).execute()) {
            responsePojo.setStatusCode(response.code());
            responsePojo.setResponseBody(response.body().string());

            return responsePojo;
        } catch (IOException e) {
            log.error("I/O exception: ", e);
        }

        return responsePojo;
    }
}