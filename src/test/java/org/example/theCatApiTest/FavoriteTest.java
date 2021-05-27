package org.example.theCatApiTest;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.example.theCatApiTest.core.ResponsePojo;
import org.example.theCatApiTest.pojo.*;
import org.example.theCatApiTest.request.BreadRequest;
import org.example.theCatApiTest.request.FavoritesRequest;
import org.example.theCatApiTest.request.ImageRequest;
import org.example.theCatApiTest.utils.Attach;
import org.example.theCatApiTest.utils.JsonConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public Attach attach = new Attach();

    @BeforeMethod
    public void setUp() {
        breadRequest = new BreadRequest();
        responsePojo = new ResponsePojo();
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
        String breedName = myBreed.getName();
        attach.setBread(breedName);
        attach.setImage(breedId);


        responsePojo = imageRequest.getImageByID(breedId);
        assertEquals(responsePojo.getStatusCode(), 200);
        List<Image> images = jsonConverter.getEntitiesFromArray(responsePojo, Image.class);
        Image image = images.get(0);
        List<Breed> responseBySearchBreadInSearchImage = image.getBreeds();
        String breedIdFromImage = responseBySearchBreadInSearchImage.get(0).getId();
        assertEquals(breedIdFromImage, breedId, "Breed ID not correct");
        String imageID = image.getId();
        String imageURL = image.getUrl();

        attach.setUrl(imageURL);

        responsePojo = favoritesRequest.saveImageInFavorites(new Favourite(imageID));
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

        attach.setBread(breedName);
        attach.setImage(breedId);
        attach.setUrl(imageURL);

        saveParamIntoFile(attach);
    }

    @Attachment
    private String saveParamIntoFile(Attach attach) {
        return attach.toString();
    }

}
