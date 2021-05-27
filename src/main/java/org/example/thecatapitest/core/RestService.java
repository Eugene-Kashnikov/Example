package org.example.thecatapitest.core;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.example.thecatapitest.utils.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

public class RestService {

    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    protected OkHttpClient client;
    protected Request req;
    protected final ResponsePojo responsePojo = new ResponsePojo();
    protected static final String AUTH = "X-Api-Key";
    public static String authValue = System.getProperty("authkey");

    public RestService() {
        this.client = new OkHttpClient.Builder()
                .readTimeout(200, TimeUnit.SECONDS)
                .addInterceptor(new AllureOkHttp3())
                .addInterceptor(new LoggingInterceptor())
                .build();
    }
}
