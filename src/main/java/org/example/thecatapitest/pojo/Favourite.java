package org.example.thecatapitest.pojo;

import lombok.Data;

@Data

public class Favourite {
    private String image_id;

    public Favourite(String image_id) {
        this.image_id = image_id;
    }
}
