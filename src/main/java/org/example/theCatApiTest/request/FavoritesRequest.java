package org.example.theCatApiTest.request;

import org.example.theCatApiTest.core.DeleteReq;
import org.example.theCatApiTest.core.GetReq;
import org.example.theCatApiTest.core.PostReq;
import io.qameta.allure.Step;
import org.example.theCatApiTest.core.ResponsePojo;
import org.example.theCatApiTest.pojo.Favourite;
import org.example.theCatApiTest.utils.ObjectMapping;

import static org.example.theCatApiTest.core.Urls.FAVORITES;

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
    public ResponsePojo saveImageInFavorites(Favourite body) {
        return postReq.doPost(FAVORITES, om.mapObjectToJsonNode(body), true);
    }

    @Step("Get my favorites image")
    public ResponsePojo getMyFavorites() {
        return getReq.doGet(FAVORITES);
    }

    @Step("Delete image from favorites")
    public ResponsePojo deleteImageOfFavorites(int param) {
        return deleteReq.doDelete(FAVORITES + String.format("/%d", param));
    }


}
