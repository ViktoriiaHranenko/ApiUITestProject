package org.pet_store.api.services;

import lombok.SneakyThrows;
import org.common.api.ApiClient;
import org.common.models.ResponseModel;
import org.pet_store.api.endpoints.UserApi;
import org.pet_store.api.models.user.User;
import retrofit2.Response;

import static org.common.models.ResponseModel.parseResponseAsObject;

public class UserService {
    private final UserApi userApi;

    public UserService(ApiClient apiClient) {

        this.userApi = apiClient.createService(UserApi.class);
    }

    @SneakyThrows
    public Response createUser(User user) {
        return userApi.createUser(user).execute();
    }

    @SneakyThrows
    public ResponseModel<User> getUserByName(String userName) {
        var response = userApi.getUserByName(userName).execute();
        return parseResponseAsObject(response, User.class);
    }


    @SneakyThrows
    public Response updateUser(User user) {
        return userApi.updateUser(user).execute();
    }

    @SneakyThrows
    public Response deleteUser(String userName) {
        return userApi.deleteUser(userName).execute();
    }
}
