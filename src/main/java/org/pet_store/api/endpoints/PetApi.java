package org.pet_store.api.endpoints;

import okhttp3.ResponseBody;
import org.pet_store.api.models.pet.Pet;
import retrofit2.Call;
import retrofit2.http.*;

public interface PetApi {
    @POST("pet")
    Call<ResponseBody> createPet(@Body Pet pet);

    @PUT("pet")
    Call<ResponseBody> updatePet(@Body Pet pet);

    @GET("pet/{id}")
    Call<ResponseBody> getPetById(@Path("id") String id);

    @GET("pet/findByStatus")
    Call<ResponseBody> findPetsByStatus(@Query("status") String status);

    @DELETE("pet/{id}")
    Call<ResponseBody> deletePet(@Path("id") String id);
}
