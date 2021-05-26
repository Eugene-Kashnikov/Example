package request;

import core.DeleteReq;
import core.GetReq;
import core.PostReq;
import io.qameta.allure.Step;
import utils.ObjectMapping;

import static core.Consts.SEARCHIMAGE;

public class ImageRequest {
    private final PostReq postReq;
    private final GetReq getReq;
    private final DeleteReq deleteReq;
    private ObjectMapping om = new ObjectMapping();

    public ImageRequest() {
        postReq = new PostReq();
        getReq = new GetReq();
        deleteReq = new DeleteReq();
    }

    @Step("Get last activity {id}")
    public core.ResponsePojo getImageByID(String name) {
        return getReq.doGet(SEARCHIMAGE);
    }
}
