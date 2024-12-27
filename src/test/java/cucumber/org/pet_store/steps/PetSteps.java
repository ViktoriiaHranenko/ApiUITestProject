package cucumber.org.pet_store.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.common.models.ResponseModel;
import org.pet_store.api.PetStoreClient;
import org.pet_store.api.models.pet.Pet;
import org.testng.Assert;

import java.util.List;

public class PetSteps {
    private PetStoreClient client = new PetStoreClient();
    private ResponseModel<Pet>response;
    private Pet createdPet;
    private List<Pet> petList;
    private int responseCode;

    /*@When("I send a request to create the pet with {string} and {string}")
    public void createPetWithNameAndStatus(String name, String status) {
        System.out.println("given");
        response = client.getPetsService().createPet(new Pet(name, status));
    }*/

    @When("I send a request to create the pet with id {string} name {string} and status {string}")
    public void iSendARequestToCreateThePetWithNameAndStatus(String id, String name, String status) {
        System.out.println("given  " + id + name + " " + status);
        response = client.getPetsService().createPet(new Pet(id, name, status));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, statusCode, "Response code is incorrect.");
    }

    @Then("the pet name should be {string}")
    public void thePetNameShouldBe(String name) {
        Assert.assertEquals(response.getBody().getName(), name, "Pet name is incorrect.");
    }

    /* @Given("a pet with name {string} and status {string} exists")
    public void aPetWithNameAndStatusExists(String name, String status) {
        client.getPetsService().createPet(new Pet(name, status));
    }

    @When("I retrieve pets by status {string}")
    public void iRetrievePetsByStatus(String status) {
        petList = client.getPetsService().findPetsByStatus(status).getBody();
    }

    @Then("the list should contain a pet with name {string}")
    public void theListShouldContainAPetWithName(String name) {
        boolean petExists = petList.stream().anyMatch(pet -> pet.getName().equals(name));
        Assert.assertTrue(petExists, "Pet not found in the list.");
    }

    @Given("a pet with ID {int} exists")
    public void aPetWithIDExists(String id) {
        client.getPetsService().createPet(new Pet(id, "TestPet", "pending"));
    }

    @When("I delete the pet with ID {int}")
    public void iDeleteThePetWithID(String id) {
        responseCode = client.getPetsService().deletePet(id).code();
    }
*/

}
