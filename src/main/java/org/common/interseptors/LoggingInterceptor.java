package org.common.interseptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String requestBodyString = null;
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            requestBodyString = buffer.readUtf8();
        }

        System.out.println("-----------------------------------------------");
        System.out.println(String.format("REQUEST: %s %s%nHeaders: %s %nBody: %s",
                request.method(), request.url(), request.headers(), requestBodyString != null ?
                        requestBodyString : "null"));

        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        String responseBodyString = responseBody != null ? responseBody.string() : "null";

        System.out.println(String.format("RESPONSE: %nStatus Code: %d%nResponse body: %s",
                response.code(), responseBodyString));
        System.out.println("-----------------------------------------------");
        return response.newBuilder()
                .body(ResponseBody.create(responseBodyString, responseBody.contentType()))
                .build();
    }
}
