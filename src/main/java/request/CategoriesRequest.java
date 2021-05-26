package request;

import core.GetReq;
import io.qameta.allure.Step;
import utils.ObjectMapping;

import static core.Consts.SEARCHCATEGORIES;

public class CategoriesRequest {

    private final GetReq getReq;

    private ObjectMapping om = new ObjectMapping();

    public CategoriesRequest() {
        getReq = new GetReq();
    }

    @Step("Get last activity {id}")
    public core.ResponsePojo getAllCategories() {
        return getReq.doGet(SEARCHCATEGORIES);
    }

}
