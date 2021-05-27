package org.example.thecatapitest.core;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;

import java.io.IOException;

@Slf4j
public class DeleteReq extends RestService {

    public ResponsePojo doDelete(String url) {
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
            log.error("There was an error during DELETE I/O exception: ", e);
            Assert.fail();
        }

        return responsePojo;
    }
}