import pojo.ResponseCategories;
import request.CategoriesRequest;
import utils.GetterCollector;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class WorkWithCategories {
    private core.ResponsePojo responsePojo;
    private GetterCollector get;
    private CategoriesRequest categoriesRequest;

    @BeforeMethod
    public void setUp() {
        responsePojo = new core.ResponsePojo();
        get = new GetterCollector();
        categoriesRequest = new CategoriesRequest();
    }

    @Test
    public void findCategoriesByName(){
        responsePojo = categoriesRequest.getAllCategories();
        assertEquals(responsePojo.getStatusCode(), 200);
        List<ResponseCategories> categories = get.getEntitiesFromArray(responsePojo, ResponseCategories.class);
        List<String> nameCategories = categories.stream().map(ResponseCategories::getName).collect(Collectors.toList());
        assertTrue(nameCategories.contains("boxes"));
    }
}
