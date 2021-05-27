package request;

import core.DeleteReq;
import core.GetReq;
import core.PostReq;
import io.qameta.allure.Step;
import pojo.Favourite;
import utils.ObjectMapping;

import static core.Urls.FAVORITES;

public class FavoritesRequest {

    private final PostReq postReq;
    private final GetReq getReq;
    private final DeleteReq deleteReq;
    private ObjectMapping om;

    public FavoritesRequest() {
        postReq = new PostReq();
        getReq = new GetReq();
        deleteReq = new DeleteReq();
        om = new ObjectMapping();
    }

    @Step("Add image in favourites")
    public core.ResponsePojo saveImageInFavorites(Favourite body) {
        return postReq.doPost(FAVORITES, om.mapObjectToJsonNode(body), true);
    }

    @Step("Get my favorites image")
    public core.ResponsePojo getMyFavorites() {
        return getReq.doGet(FAVORITES);
    }

    @Step("Delete image from favorites")
    public core.ResponsePojo deleteImageOfFavorites(int param) {
        return deleteReq.doDelete(FAVORITES + String.format("/%d", param));
    }


}
