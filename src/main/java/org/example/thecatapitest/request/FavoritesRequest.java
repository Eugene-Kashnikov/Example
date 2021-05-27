package org.example.thecatapitest.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.example.thecatapitest.core.DeleteReq;
import org.example.thecatapitest.core.GetReq;
import org.example.thecatapitest.core.PostReq;
import org.example.thecatapitest.core.ResponsePojo;
import org.example.thecatapitest.pojo.Favourite;

import static org.example.thecatapitest.core.Urls.FAVORITES;

public class FavoritesRequest {

    private final PostReq postReq;
    private final GetReq getReq;
    private final DeleteReq deleteReq;
    private ObjectMapper om;

    public FavoritesRequest() {
        postReq = new PostReq();
        getReq = new GetReq();
        deleteReq = new DeleteReq();
        om = new ObjectMapper();
    }

    @Step("Add image in favourites")
    public ResponsePojo saveImageInFavorites(Favourite body) {
        return postReq.doPost(FAVORITES, om.convertValue(body, JsonNode.class), true);
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
