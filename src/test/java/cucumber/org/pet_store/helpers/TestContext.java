package cucumber.org.pet_store.helpers;

import java.util.ArrayList;
import java.util.List;

public class TestContext {
    private static final List<String> createdPetsIds = new ArrayList<>();

    public static void addCreatedPetId(String id) {
        createdPetsIds.add(id);
    }

    public static List<String> getCreatedPetsIds() {
        return new ArrayList<>(createdPetsIds);
    }
}
