package org.pet_store.api;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.common.api.ApiClient;
import org.pet_store.api.services.UserService;
import org.config.ProjectConfig;
import org.pet_store.api.services.PetsService;

@Getter
public class PetStoreClient {

    private final PetsService petsService;
    private final UserService userService;

    public PetStoreClient() {
        ApiClient client = new ApiClient(getConfig());

        this.petsService = new PetsService(client);
        this.userService = new UserService(client);
    }

    private static String getConfig() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
        return config.petstoreApiBaseUrl();
    }

}
