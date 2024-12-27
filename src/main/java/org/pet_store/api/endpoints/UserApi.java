package org.pet_store.api.endpoints;

import okhttp3.ResponseBody;
import org.pet_store.api.models.user.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {
    @POST("user")
    Call<ResponseBody> createUser(@Body User user);

    @PUT("user/{userName}")
    Call<ResponseBody> updateUser(@Body User user);

    @GET("user/{userName}")
    Call<ResponseBody> getUserByName(@Path("userName") String userName);

    @DELETE("user/{userName}")
    Call<ResponseBody> deleteUser(@Path("userName") String userName);
}
