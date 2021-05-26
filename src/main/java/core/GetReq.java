package core;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class GetReq extends RestService {

    public core.ResponsePojo doGet(String url) {
        req = new Request.Builder()
                .url(url)
                .addHeader(AUTH, authValue)
                .get()
                .build();

        try (Response response = client.newCall(req).execute()) {
            responsePojo.setStatusCode(response.code());
            var local = response.body().string();
            responsePojo.setResponseBody(local);

            return responsePojo;
        } catch (IOException e) {
            log.error("I/O exception: ", e);
        }

        return responsePojo;
    }

}
