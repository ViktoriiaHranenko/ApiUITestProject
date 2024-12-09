package org.common.models;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResponseModel<T> {
    private int statusCode;
    private Map<String, List<String>> headers;
    private T body;
    private ApiError error;

    public ResponseModel(int statusCode, Map<String, List<String>> headers, T body, ApiError error) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.error = error;
    }

    @SneakyThrows
    public static <T> ResponseModel<List<T>> parseResponseAsList(Response<ResponseBody> response, Type listType) {
        ApiError error = checkSuccess(response);
        if (error != null) {
            return new ResponseModel<>(response.code(), response.headers().toMultimap(), null, error);
        }
        try (ResponseBody responseBody = response.body()) {
            List<T> bodyList = new Gson().fromJson(responseBody.string(), listType);
            return new ResponseModel<>(response.code(), response.headers().toMultimap(), bodyList, null);

        }
    }

    @SneakyThrows
    public static <T> ResponseModel<T> parseResponseAsObject(Response<ResponseBody> response, Class<T> type) {
        ApiError error = checkSuccess(response);
        if (error != null) {
            return new ResponseModel<>(response.code(), response.headers().toMultimap(), null, error);
        }

        try (ResponseBody responseBody = response.body()) {
            T body = new Gson().fromJson(responseBody.string(), type);
            return new ResponseModel<>(response.code(), response.headers().toMultimap(), body, null);
        }
    }

    @SneakyThrows
    private static ApiError checkSuccess(Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            return null;
        }
        try (ResponseBody errorBody = response.errorBody()) {
            String errorMessage = errorBody != null ? errorBody.string() : "Unknown error";
            return new ApiError(response.code(), errorMessage);
        }
    }
}
