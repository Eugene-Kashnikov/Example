package org.example.theCatApiTest.request;

import org.example.theCatApiTest.core.GetReq;
import io.qameta.allure.Step;
import org.example.theCatApiTest.core.ResponsePojo;

import static org.example.theCatApiTest.core.Urls.SEARCH_IMAGE;

public class ImageRequest {
    private final GetReq getReq;


    public ImageRequest() {
        getReq = new GetReq();
    }

    @Step("Get last activity {id}")
    public ResponsePojo getImageByID(String param) {
        return getReq.doGet(SEARCH_IMAGE + param);
    }
}
