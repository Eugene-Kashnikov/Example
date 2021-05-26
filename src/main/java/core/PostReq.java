package core;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
    public class PostReq extends RestService {
        protected final ResponseBytes responseBytes = new ResponseBytes();
        private RequestBody body;

        public core.ResponsePojo doPost(String url,JsonNode bodys, boolean isBodyRequired) {
            String bodyStr = null;
            String emptyBody = "{}";

            if (!isBodyRequired) {
                body = RequestBody.create(emptyBody, JSON);
                req = new Request.Builder()
                        .url("https://api.thecatapi.com/v1/favourites")
                        .post(body)
                        .addHeader(AUTH, authValue)
                        .addHeader("Content-Type", "application/json")
                        .build();
                try (Response response = client.newCall(req).execute()) {
                    responsePojo.setStatusCode(response.code());
                    responsePojo.setResponseBody(response.body().string());
                } catch (IOException e) {
                    log.error("I/O exception: ", e);
                }
            } else {
                body = RequestBody.create(bodys.toString(), JSON);
                req = new Request.Builder()
                        .url(url)
                        .post(body)
                        .addHeader(AUTH, authValue)
                        .addHeader("Content-Type", "application/json")
                        .build();
                try (Response response = client.newCall(req).execute()) {
                    responsePojo.setStatusCode(response.code());
                    responsePojo.setResponseBody(response.body().string());
                } catch (IOException e) {
                    log.error("I/O exception: ", e);
                }
            }
            return responsePojo;
        }
}
