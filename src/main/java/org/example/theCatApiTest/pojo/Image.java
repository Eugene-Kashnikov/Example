package org.example.theCatApiTest.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class Image {
    private String id;
    private String url;
    private List<Breed> breeds;
    private Category categories;
}
