package tests.users.positive;

import factories.UserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.UserSteps;
import static org.hamcrest.Matchers.equalTo;


public class DeleteUser {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Delete user")
    @DisplayName("Delete user")
    @Test
    void deleteUserTest () {
        User user = UserFactory.createRandomUser();
        Response createResponse = UserSteps.createUser(user);
        createResponse.then()
                .statusCode(200)
                .body("message", equalTo(user.getId().toString()));

        Response deleteResponse = UserSteps.deleteUser(user.getUsername());
        deleteResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(user.getUsername()));

        Response getUserResponse = UserSteps.getUserByUserName(user.getUsername());
        getUserResponse.then()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("User not found"));
    }
}
