package org.example.thecatapitest.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePojo {

    private int statusCode;
    private String responseBody;
}
