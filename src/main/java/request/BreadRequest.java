package request;

import core.GetReq;
import io.qameta.allure.Step;

import static core.Urls.SEARCH_BREED;

public class BreadRequest {

    private final GetReq getReq;

    public BreadRequest() {
        getReq = new GetReq();
    }

    @Step("Get breed by name")
    public core.ResponsePojo getBreadByName() {
        return getReq.doGet(SEARCH_BREED);
    }
}
