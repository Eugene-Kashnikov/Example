import core.ResponsePojo;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.Category;
import request.CategoriesRequest;
import utils.JsonConverter;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class CategoryTest {
    private ResponsePojo responsePojo;
    private JsonConverter jsonConverter;
    private CategoriesRequest categoriesRequest;

    @BeforeMethod
    public void setUp() {
        responsePojo = new core.ResponsePojo();
        jsonConverter = new JsonConverter();
        categoriesRequest = new CategoriesRequest();
    }

    @Test
    public void findCategoriesByNameTest() {

        responsePojo = categoriesRequest.getAllCategories();
        assertEquals(responsePojo.getStatusCode(), 200);
        List<Category> categories = jsonConverter.getEntitiesFromArray(responsePojo, Category.class);
        List<String> nameCategories = categories.stream().map(Category::getName).collect(Collectors.toList());
        assertTrue(nameCategories.contains("boxes"));
    }
}
