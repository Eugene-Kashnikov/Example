import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.*;
import request.BreadRequest;
import request.FavoritesRequest;
import request.ImageRequest;
import utils.GetterCollector;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class WorkWithImageInFavorites {

    private core.ResponsePojo responsePojo;
    private GetterCollector get;
    private BreadRequest breadRequest;
    private ImageRequest imageRequest;
    private FavoritesRequest favoritesRequest;

    @BeforeMethod
    public void setUp() {
        breadRequest = new BreadRequest();
        responsePojo = new core.ResponsePojo();
        get = new GetterCollector();
        imageRequest = new ImageRequest();
        favoritesRequest = new FavoritesRequest();
    }

    @Test

    public void workWithBreed () {

        responsePojo = breadRequest.getBreadByName();
        List<ResponseBySearchBread> myBreads = get.getEntitiesFromArray(responsePojo, ResponseBySearchBread.class);
        ResponseBySearchBread myBreed = myBreads.get(0);
        assertEquals(responsePojo.getStatusCode(), 200);
        String breedId = myBreed.getId();

        responsePojo = imageRequest.getImageByID(breedId);
        assertEquals(responsePojo.getStatusCode(), 200);
        List<ResponseBySearchImage> responseBySearchImages = get.getEntitiesFromArray(responsePojo, ResponseBySearchImage.class);
        ResponseBySearchImage responseBySearchImage = responseBySearchImages.get(0);
        List<ResponseBySearchBread> responseBySearchBreadInSearchImage = responseBySearchImage.getBreeds();
        String breedIdFromImage = responseBySearchBreadInSearchImage.get(0).getId();
        assertEquals(breedIdFromImage,breedId, "Breed ID not correct");
        String imageID = responseBySearchImage.getId();
        String imageURL = responseBySearchImage.getUrl();

        responsePojo = favoritesRequest.saveImageInFavorites(bodyForSearch(imageID));
        assertEquals(responsePojo.getStatusCode(), 200);
        FavouritesResponse favourites = get.getEntity(responsePojo, FavouritesResponse.class);
        assertTrue(favourites.getMessage().contains("SUCCESS"));
        int imageIdFavourites = favourites.getId();

        responsePojo = favoritesRequest.getMyFavorites();
        assertEquals(responsePojo.getStatusCode(), 200);
        List<ImageInFavorites> imageInFavorites = get.getEntitiesFromArray(responsePojo, ImageInFavorites.class);
        List<Integer> imageIdsFavorites = imageInFavorites.stream().map(ImageInFavorites::getId).collect(Collectors.toList());
        assertTrue(imageIdsFavorites.contains(imageIdFavourites));
        List<String> imageIdsInFavorites = imageInFavorites.stream().map(ImageInFavorites::getImage_id).collect(Collectors.toList());
        assertTrue(imageIdsInFavorites.contains(imageID));

        responsePojo = favoritesRequest.deleteImageOfFavorites(imageIdFavourites);
        assertEquals(responsePojo.getStatusCode(), 200);
        FavoriteDelete favoriteDelete = get.getEntity(responsePojo, FavoriteDelete.class);
        assertTrue(favoriteDelete.getMessage().contains("SUCCESS"));

        responsePojo = favoritesRequest.getMyFavorites();
        assertEquals(responsePojo.getStatusCode(), 200);
        List<ImageInFavorites> imageInFavoritesAfterDelete = get.getEntitiesFromArray(responsePojo, ImageInFavorites.class);
        List<Integer> imageIdsFavoritesAfterDelete = imageInFavoritesAfterDelete.stream().map(ImageInFavorites::getId).collect(Collectors.toList());
        assertFalse(imageIdsFavoritesAfterDelete.contains(imageIdFavourites));
    }

    private FavouriteRequest bodyForSearch(String query) {
        return FavouriteRequest.builder()
                .image_id(query)
                .build();
    }
}
