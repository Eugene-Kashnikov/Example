package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class FavouriteRequest {
    private String image_id;
}
