package testng.org.pet_store.regression.pet;

import org.pet_store.api.PetStoreClient;
import org.pet_store.api.models.pet.Pet;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.pet_store.api.models.pet.PetsAvailability.AVAILABLE;
import static org.pet_store.api.models.pet.PetsAvailability.PENDING;

public class PetTest {
    private PetStoreClient client;

    private static final String ID_1 = "922337203685477588";
    private static final String PET_NAME_1 = "TestPet1";

    private static final String ID_2 = "922337203685477538";
    private static final String PET_NAME_2 = "TestPet2";
    private Pet petInPendingStatus, petInAvailableStatus, petInSoldStatus;

    @BeforeClass
    public void init() {
        client = new PetStoreClient();
        petInPendingStatus = new Pet(ID_1, PET_NAME_1, PENDING.getStatus());
        petInAvailableStatus = new Pet(ID_2, PET_NAME_2, AVAILABLE.getStatus());
    }

    @Test
    public void createPetTest() {
        SoftAssert sa = new SoftAssert();
        var createdPet = client.getPetsService().createPet(petInPendingStatus);
        sa.assertEquals(createdPet.getStatusCode(), 200, "Response code is incorrect. Expect 200");
        sa.assertEquals(createdPet.getBody().getName(), PET_NAME_1, "Name of created pet is incorrect");
        sa.assertAll();
    }

    @Test(dependsOnMethods = "createPetTest")
    public void createdPetIsPresentInPetsList() {
        List<Pet> createdPets = client.getPetsService().findPetsByStatus(PENDING.getStatus()).getBody();
        var pet = createdPets.stream()
                .filter(testPet ->
                        testPet.getName() != null && testPet.getName().contains(PET_NAME_1))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Pet with name " + PET_NAME_1 + " is not found"));
        Assert.assertEquals(pet.getId(), ID_1, "Pet ID is incorrect");
    }

    @Test
    public void getPetTest() {
        client.getPetsService().createPet(petInAvailableStatus);
        var pet = client.getPetsService().getPetById(ID_2);
        Assert.assertEquals(pet.getBody().getName(), PET_NAME_2, "Pet name is incorrect");
    }

    @Test(dependsOnMethods = "createPetTest")
    public void deletePetTest() {
        var response = client.getPetsService().deletePet(ID_1);
        Assert.assertEquals(response.code(), 200, "Error code is incorrect");
    }

    @AfterClass
    public void cleanup() {
        if (client.getPetsService().getPetById(ID_2).getStatusCode() == 200) {
            client.getPetsService().deletePet(ID_2);
        }
    }
}
