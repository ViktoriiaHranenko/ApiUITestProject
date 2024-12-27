package org.pet_store.api.services;

import lombok.SneakyThrows;
import org.common.api.ApiClient;
import org.common.models.ResponseModel;
import org.pet_store.api.endpoints.UserApi;
import org.pet_store.api.models.user.User;

import static org.common.models.ResponseModel.parseResponseAsObject;

public class UserService {
    private final UserApi userApi;

    public UserService(ApiClient apiClient) {

        this.userApi = apiClient.createService(UserApi.class);
    }

    @SneakyThrows
    public ResponseModel<User> createUser(User user) {
        var response = userApi.createUser(user).execute();
        return parseResponseAsObject(response, User.class);
    }
}
