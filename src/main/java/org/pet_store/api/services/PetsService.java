package org.pet_store.api.services;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.common.api.ApiClient;
import org.common.models.ResponseModel;
import org.openqa.selenium.json.TypeToken;
import org.pet_store.api.endpoints.PetApi;
import org.pet_store.api.models.pet.Pet;
import retrofit2.Response;

import java.lang.reflect.Type;
import java.util.List;

import static org.common.models.ResponseModel.parseResponseAsList;
import static org.common.models.ResponseModel.parseResponseAsObject;

public class PetsService {
    private final PetApi petApi;

    public PetsService(ApiClient apiClient) {
        this.petApi = apiClient.createService(PetApi.class);
    }

    @SneakyThrows
    public ResponseModel<Pet> createPet(Pet pet) {
        var response = petApi.createPet(pet).execute();
        return parseResponseAsObject(response, Pet.class);
    }

    @SneakyThrows
    public ResponseModel<Pet> getPetById(String id) {
        Response<ResponseBody> response = petApi.getPetById(id).execute();
        return parseResponseAsObject(response, Pet.class);
    }

    @SneakyThrows
    public Response deletePet(String id) {
        return petApi.deletePet(id).execute();
    }

    @SneakyThrows
    public Pet getPetByNameAndStatus(String name, String status) {
        List<Pet> pets = findPetsByStatus(status).getBody();
        return pets.stream()
                .filter(pet ->
                        pet.getName() != null && pet.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Pet with name " + name + " is not found"));
    }

    @SneakyThrows
    public ResponseModel<List<Pet>> findPetsByStatus(String status) {
        Response<ResponseBody> response = petApi.findPetsByStatus(status).execute();
        Type listType = new TypeToken<List<Pet>>() {}.getType();
        return parseResponseAsList(response, listType);
    }
}
