package org.example.thecatapitest.request;

import org.example.thecatapitest.core.GetReq;
import io.qameta.allure.Step;
import org.example.thecatapitest.core.ResponsePojo;

import static org.example.thecatapitest.core.Urls.SEARCH_BREED;

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
