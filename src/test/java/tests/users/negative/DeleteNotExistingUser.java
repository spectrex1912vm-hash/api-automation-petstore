package tests.users.negative;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.UserSteps;

import java.util.UUID;


public class DeleteNotExistingUser {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Delete non-existing user")
    @DisplayName("Delete not existing user test")
    @Test
    void deleteNotExistingUserTest() {
        Response deleteResponse = UserSteps.deleteUser(
                UUID.randomUUID().toString());
        deleteResponse.then()
                .statusCode(404);
        // Swagger Petstore returns 404 with empty body for DELETE non-existing user

    }
}
