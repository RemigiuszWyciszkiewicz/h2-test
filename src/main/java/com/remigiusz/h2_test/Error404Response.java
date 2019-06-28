package com.remigiusz.h2_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Error404Response {


    private String msg;
    private HttpStatus httpStatus;
}
