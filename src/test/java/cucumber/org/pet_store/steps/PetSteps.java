package cucumber.org.pet_store.steps;

import cucumber.org.pet_store.helpers.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.common.models.ResponseModel;
import org.pet_store.api.PetStoreClient;
import org.pet_store.api.models.pet.Pet;
import org.testng.Assert;

public class PetSteps {
    private final PetStoreClient client = new PetStoreClient();
    private ResponseModel<Pet> createdPet;

    @When("I create the pet with id {string} name {string} and status {string}")
    public void iSendARequestToCreateThePetWithNameAndStatus(String id, String name, String status) {
        createdPet = client.getPetsService().createPet(new Pet(id, name, status));
        TestContext.addCreatedPetId(createdPet.getBody().getId());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        var responseCode = createdPet.getStatusCode();
        Assert.assertEquals(responseCode, statusCode, "Response code is incorrect.");
    }

    @Then("the pet name should be {string}")
    public void thePetNameShouldBe(String name) {
        Assert.assertEquals(createdPet.getBody().getName(), name, "Pet name is incorrect.");
    }
}
