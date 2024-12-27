package testng.org.pet_store.regression.pet;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.common.models.ResponseModel;
import org.pet_store.api.PetStoreClient;
import org.pet_store.api.models.pet.Pet;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.pet_store.api.models.pet.PetsAvailability.*;

@Feature("Pet")
public class PetTest {
    private PetStoreClient client;

    private static final String ID_1 = "922337203685477581";
    private static final String PET_NAME_1 = "petInPendingStatus";

    private static final String ID_2 = "922337203685477532";
    private static final String PET_NAME_2 = "petInAvailableStatus";

    private static final String ID_3 = "922337203685477533";
    private static final String PET_NAME_3 = "petInSoldStatus";
    private List<Pet> pets;
    private ResponseModel<Pet> createdPet;

    @BeforeClass
    public void init() {
        client = new PetStoreClient();
        pets = List.of(
                new Pet(ID_1, PET_NAME_1, PENDING.getStatus()),
                new Pet(ID_2, PET_NAME_2, AVAILABLE.getStatus()),
                new Pet(ID_3, PET_NAME_3, SOLD.getStatus())
        );
    }

    @Test(dataProvider = "petDataProvider")
    @Description("Test pet creation with different statuses")
    public void createPetTest(String description, Pet pet) {
        SoftAssert sa = new SoftAssert();
        Allure.step("Create a pet ", () ->
        {
            createdPet = client.getPetsService().createPet(pet);
        });
        validatePet(createdPet, pet, sa);
        sa.assertAll();
    }


    @Test(dependsOnMethods = "createPetTest")
    @Step
    @Description("Test that pet with pending status is presented in pets list")
    public void createdPendingPetIsPresentInPetsListTest() {
        Allure.step("Get pets in pending status");
        List<Pet> createdPets = client.getPetsService().findPetsByStatus(PENDING.getStatus()).getBody();
        Allure.step("Find created previously pet by name");
        var pet = createdPets.stream()
                .filter(testPet ->
                        testPet.getName() != null && testPet.getName().contains(PET_NAME_1))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Pet with name " + PET_NAME_1 + " is not found"));
        Allure.step("Check that petId is correct");
        Assert.assertEquals(pet.getId(), ID_1, "Pet ID is incorrect");
    }

    @Test(dependsOnMethods = "createPetTest")
    @Description("Test pet details")
    public void getPetTest() {
        SoftAssert sa = new SoftAssert();
        Allure.step("Get pet by id");
        var pet = client.getPetsService().getPetById(pets.get(1).getId());
        validatePet(pet, pets.get(1), sa);
        sa.assertAll();
    }

    @Test(dependsOnMethods = "createPetTest")
    @Description("Test that pet can be deleted")
    public void deletePetTest() {
        Allure.step("Delete pet");
        var response = client.getPetsService().deletePet(ID_1);
        Allure.step("Check response");
        Assert.assertEquals(response.code(), 200, "Error code is incorrect");
    }

    @DataProvider(name = "petDataProvider")
    public Object[][] petDataProvider() {
        return pets.stream()
                .map(pet -> new Object[]{pet.getDescription(), pet})
                .toArray(Object[][]::new);
    }

    @Step("Check pet parameters")
    private void validatePet(ResponseModel<Pet> createdPet, Pet expectedPet, SoftAssert sa) {
        sa.assertEquals(createdPet.getStatusCode(), 200, "Response code is incorrect. Expect 200");
        sa.assertEquals(createdPet.getBody().getName(), expectedPet.getName(), "Name of created pet is incorrect");
        sa.assertEquals(createdPet.getBody().getId(), expectedPet.getId(), "Id of created pet is incorrect");
        sa.assertEquals(createdPet.getBody().getStatus(), expectedPet.getStatus(), "Status of created pet is incorrect");
    }

    @AfterClass
    public void cleanup() {
        pets.forEach(pet ->
        {
            if (client.getPetsService().getPetById(pet.getId()).getStatusCode() == 200) {
                client.getPetsService().deletePet(pet.getId());
            }
        });
    }
}
