package request;

import core.DeleteReq;
import core.GetReq;
import core.PostReq;
import io.qameta.allure.Step;
import utils.ObjectMapping;

import static core.Consts.SEARCHBREED;

public class BreadRequest {

    private final PostReq postReq;
    private final GetReq getReq;
    private final DeleteReq deleteReq;
    private ObjectMapping om = new ObjectMapping();

    public BreadRequest() {
        postReq = new PostReq();
        getReq = new GetReq();
        deleteReq = new DeleteReq();
    }

    @Step("Get breed by name")
    public core.ResponsePojo getBreadByName() {

        return getReq.doGet(SEARCHBREED);
    }

}
