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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class UserLogout {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Logout current user")
    @DisplayName("Logout user")
    @Test
    void logoutCurrentUserTest () {
        User user = UserFactory.createRandomUser();

        Response createResponse = UserSteps.createUser(user);
        createResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(user.getId().toString()));


        Response loginResponse = UserSteps.login(user);
        loginResponse.then()
                .body("code", equalTo(200))
                .body("message", containsString("logged in user session"));

        Response logoutResponse = UserSteps.logout();
        logoutResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("ok"));



    }
}
