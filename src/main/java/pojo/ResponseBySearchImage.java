package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class ResponseBySearchImage {
    private String id;
    private String url;
    private List<ResponseBySearchBread> breeds;
    private ResponseCategories categories;
}
