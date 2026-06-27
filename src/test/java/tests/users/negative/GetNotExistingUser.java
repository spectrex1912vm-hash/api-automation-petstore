package tests.users.negative;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.UserSteps;
import java.util.UUID;
import static org.hamcrest.Matchers.equalTo;

public class GetNotExistingUser {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Get non-existing user")
    @DisplayName("Get not existing user test")
    @Test
    void getNotExistingUserTest() {
        Response getresponse = UserSteps.getUserByUserName(
                UUID.randomUUID().toString()
        );

        getresponse.then()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("User not found"));

    }
}
