package request;

import core.GetReq;
import io.qameta.allure.Step;

import static core.Urls.SEARCH_IMAGE;

public class ImageRequest {
    private final GetReq getReq;


    public ImageRequest() {
        getReq = new GetReq();
    }

    @Step("Get last activity {id}")
    public core.ResponsePojo getImageByID(String param) {
        return getReq.doGet(SEARCH_IMAGE + param);
    }
}
