package cucumber.org.pet_store.helpers;

import io.cucumber.java.After;
import org.pet_store.api.PetStoreClient;

import java.util.List;

public class Hooks {
    private final PetStoreClient client = new PetStoreClient();

    @After
    public void cleanup() {
        List<String> createdPetsIds = TestContext.getCreatedPetsIds();
        for (String petId : createdPetsIds) {
            if (client.getPetsService().getPetById(petId).getStatusCode() == 200) {
                client.getPetsService().deletePet(petId);
            }
        }
    }
}
