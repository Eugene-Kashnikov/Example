package core;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class RestService {

        protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        protected OkHttpClient client;
        protected Request req;
        protected Response response;
        protected final core.ResponsePojo responsePojo = new core.ResponsePojo();
        protected static final String AUTH = "X-Api-Key";
        public String authValue = "c87c0567-5044-4893-9b4b-f010ffea0e25";
//                getProperties("authKey");

        public RestService() {
                this.client = new OkHttpClient.Builder()
                    .readTimeout(200, TimeUnit.SECONDS)
                    .addInterceptor(new AllureOkHttp3())
                    .addInterceptor(new LoggingInterceptor())
                    .build();
        }
//        private String getProperties(String authKey) {
//                String properties = System.getProperties(authKey);
//                return toString(properties);
//        }
}
