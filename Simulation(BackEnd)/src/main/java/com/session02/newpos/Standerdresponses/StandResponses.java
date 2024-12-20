package com.session02.newpos.Standerdresponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandResponses {
    private int code;
    private String message;
    private Object data;


}
