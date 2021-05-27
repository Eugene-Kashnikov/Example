import core.ResponsePojo;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.*;
import request.BreadRequest;
import request.FavoritesRequest;
import request.ImageRequest;
import utils.JsonConverter;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

@Slf4j
public class FavoriteTest {

    private ResponsePojo responsePojo;
    private JsonConverter jsonConverter;
    private BreadRequest breadRequest;
    private ImageRequest imageRequest;
    private FavoritesRequest favoritesRequest;

    @BeforeMethod
    public void setUp() {
        breadRequest = new BreadRequest();
        responsePojo = new core.ResponsePojo();
        jsonConverter = new JsonConverter();
        imageRequest = new ImageRequest();
        favoritesRequest = new FavoritesRequest();
    }

    @Test

    public void FavoriteTest() {

        responsePojo = breadRequest.getBreadByName();
        List<Breed> myBreads = jsonConverter.getEntitiesFromArray(responsePojo, Breed.class);
        Breed myBreed = myBreads.get(0);
        assertEquals(responsePojo.getStatusCode(), 200);
        String breedId = myBreed.getId();

        responsePojo = imageRequest.getImageByID(breedId);
        assertEquals(responsePojo.getStatusCode(), 200);
        List<Image> images = jsonConverter.getEntitiesFromArray(responsePojo, Image.class);
        Image image = images.get(0);
        List<Breed> responseBySearchBreadInSearchImage = image.getBreeds();
        String breedIdFromImage = responseBySearchBreadInSearchImage.get(0).getId();
        assertEquals(breedIdFromImage, breedId, "Breed ID not correct");
        String imageID = image.getId();
        String imageURL = image.getUrl();

        responsePojo = favoritesRequest.saveImageInFavorites(bodyForSearch(imageID));
        assertEquals(responsePojo.getStatusCode(), 200);
        Favourites favourites = jsonConverter.getEntity(responsePojo, Favourites.class);
        assertTrue(favourites.getMessage().contains("SUCCESS"));
        int imageIdFavourites = favourites.getId();

        responsePojo = favoritesRequest.getMyFavorites();
        assertEquals(responsePojo.getStatusCode(), 200);
        List<FavouritesImage> favouritesImageInFavorites = jsonConverter.getEntitiesFromArray(responsePojo, FavouritesImage.class);
        List<Integer> imageIdsFavorites = favouritesImageInFavorites.stream().map(FavouritesImage::getId).collect(Collectors.toList());
        assertTrue(imageIdsFavorites.contains(imageIdFavourites));
        List<String> imageIdsInFavorites = favouritesImageInFavorites.stream().map(FavouritesImage::getImage_id).collect(Collectors.toList());
        assertTrue(imageIdsInFavorites.contains(imageID));

        responsePojo = favoritesRequest.deleteImageOfFavorites(imageIdFavourites);
        assertEquals(responsePojo.getStatusCode(), 200);
        FavoriteDelete favoriteDelete = jsonConverter.getEntity(responsePojo, FavoriteDelete.class);
        assertTrue(favoriteDelete.getMessage().contains("SUCCESS"));

        responsePojo = favoritesRequest.getMyFavorites();
        assertEquals(responsePojo.getStatusCode(), 200);
        List<FavouritesImage> favouritesImageAfterDelete = jsonConverter.getEntitiesFromArray(responsePojo, FavouritesImage.class);
        List<Integer> imageIdsFavoritesAfterDelete = favouritesImageAfterDelete.stream().map(FavouritesImage::getId).collect(Collectors.toList());
        assertFalse(imageIdsFavoritesAfterDelete.contains(imageIdFavourites));
    }

    private Favourite bodyForSearch(String query) {
        return Favourite.builder()
                .image_id(query)
                .build();
    }
}
