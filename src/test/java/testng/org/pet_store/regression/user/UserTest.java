package testng.org.pet_store.regression.user;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.pet_store.api.PetStoreClient;
import org.pet_store.api.models.user.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Feature("User")
public class UserTest {
    private PetStoreClient client;
    private User user;

    @BeforeClass
    public void init() {
        client = new PetStoreClient();
        user = new User("1111", "testUserV", "TestUserFirstName", "TestUserSecondName",
                "testUser@mail.com", "string", "0043500233", 0);
    }

    @Test
    @Description("Test user creation")
    public void createUserTest() {
        SoftAssert sa = new SoftAssert();
        Allure.step("Create an user");
        var createdUser = client.getUserService().createUser(user);
        Allure.step("Check user parameters");
        sa.assertEquals(createdUser.code(), 200, "Response code is incorrect. Expect 200");
    }

    @Test(dependsOnMethods = "createUserTest")
    @Description("Check user can be found by name")
    public void getUserTest() {
        SoftAssert sa = new SoftAssert();
        Allure.step("Get user by name");
        var receivedUser = client.getUserService().getUserByName(user.getUsername());
        Allure.step("Check user parameters");
        sa.assertEquals(receivedUser.getStatusCode(), 200, "Response code is incorrect. Expect 200");
        sa.assertEquals(receivedUser.getBody().getId(), user.getId(), "User ID is incorrect");
        sa.assertEquals(receivedUser.getBody().getUsername(), user.getUsername(), "Username is incorrect");
        sa.assertEquals(receivedUser.getBody().getEmail(), user.getEmail(), "Email is incorrect");
        sa.assertEquals(receivedUser.getBody().getPhone(), user.getPhone(), "Phone is incorrect");
        sa.assertEquals(receivedUser.getBody().getUserStatus(), user.getUserStatus(), "User status is incorrect");
        sa.assertEquals(receivedUser.getBody().getFirsName(), user.getFirsName(), "First name is incorrect");
        sa.assertEquals(receivedUser.getBody().getLastName(), user.getLastName(), "Last name is incorrect");
        sa.assertAll();
    }

    @Test(dependsOnMethods = "createUserTest")
    @Description("Check that user params can be updated")
    public void updateUserTest() {
        SoftAssert sa = new SoftAssert();
        Allure.step("Update user");
        user.setLastName("NewLastName");
        var response = client.getUserService().updateUser(user);
        Allure.step("Check response");
        sa.assertEquals(response.code(), 200, "Response code is incorrect. Expect 200");
        Allure.step("Get user parameters");
        var updatedUser = client.getUserService().getUserByName(user.getUsername());
        Allure.step("Check user parameter is updated");
        sa.assertEquals(updatedUser.getBody().getLastName(), user.getLastName(), "User last name is incorrect");
        sa.assertAll();
    }

    @Test(dependsOnMethods = "updateUserTest")
    @Description("Check that user can be deleted")
    public void deleteUserTest() {
        Allure.step("Delete USER");
        var response = client.getUserService().deleteUser(user.getUsername());
        Allure.step("Check response");
        Assert.assertEquals(response.code(), 200, "Error code is incorrect");
    }
}
