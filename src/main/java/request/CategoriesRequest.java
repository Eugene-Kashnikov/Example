package request;

import core.GetReq;
import io.qameta.allure.Step;

import static core.Urls.SEARCH_CATEGORIES;

public class CategoriesRequest {

    private final GetReq getReq;

    public CategoriesRequest() {

        getReq = new GetReq();
    }

    @Step("Get last activity {id}")
    public core.ResponsePojo getAllCategories() {
        return getReq.doGet(SEARCH_CATEGORIES);
    }

}
