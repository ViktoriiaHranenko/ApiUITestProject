package org.common.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private int statusCode;
    private String message;

    public ApiError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
