package org.example.theCatApiTest.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class FavouritesImage {
    private int id;
    private String url;
    private String sub_id;
    private String created_at;
    private String image_id;
}
