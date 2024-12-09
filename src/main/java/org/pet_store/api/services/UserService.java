package org.pet_store.api.services;

import org.common.api.ApiClient;
import org.pet_store.api.endpoints.UserApi;

public class UserService {
    private final UserApi userApi;

    public UserService(ApiClient apiClient) {

        this.userApi = apiClient.createService(UserApi.class);
    }
}
