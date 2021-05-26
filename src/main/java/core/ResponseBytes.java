package core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseBytes {
    private int statusCode;
    private byte[] responseByte;
}
