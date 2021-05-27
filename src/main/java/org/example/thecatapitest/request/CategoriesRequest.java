package org.example.thecatapitest.request;

import org.example.thecatapitest.core.GetReq;
import io.qameta.allure.Step;
import org.example.thecatapitest.core.ResponsePojo;

import static org.example.thecatapitest.core.Urls.SEARCH_CATEGORIES;

public class CategoriesRequest {

    private final GetReq getReq;

    public CategoriesRequest() {

        getReq = new GetReq();
    }

    @Step("Get last activity {id}")
    public ResponsePojo getAllCategories() {
        return getReq.doGet(SEARCH_CATEGORIES);
    }

}
