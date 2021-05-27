package org.example.theCatApiTest.request;

import org.example.theCatApiTest.core.GetReq;
import io.qameta.allure.Step;
import org.example.theCatApiTest.core.ResponsePojo;

import static org.example.theCatApiTest.core.Urls.SEARCH_BREED;

public class BreadRequest {

    private final GetReq getReq;

    public BreadRequest() {
        getReq = new GetReq();
    }

    @Step("Get breed by name")
    public ResponsePojo getBreadByName() {
        return getReq.doGet(SEARCH_BREED);
    }
}
