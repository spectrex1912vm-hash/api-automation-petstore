package tests.users.e2e;

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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLifecycleE2E {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("User lifecycle")
    @DisplayName("User e2e Test")
    @Test
    void userFullLifecycleTest() {
        User user = UserFactory.createRandomUser();
        Response createResponse = UserSteps.createUser(user);
        createResponse.then()
                .statusCode(200)
                .body("message", equalTo(user.getId().toString()));

        Response loginResponse = UserSteps.login(user);
        loginResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", containsString("logged in user session"));

        User actualUser =
                UserSteps.getUserByUserName(user.getUsername())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(User.class);
        assertAll(
                () -> assertEquals(
                        user.getId(),
                        actualUser.getId()
                ),
                () -> assertEquals(
                        user.getUsername(),
                        actualUser.getUsername()
                ),
                () -> assertEquals(
                        user.getEmail(),
                        actualUser.getEmail()
                )
        );

        User updatedUser = UserFactory.updateRandomUser(user);

        Response updateResponse = UserSteps.updateUser(user.getUsername(), updatedUser);
        updateResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(updatedUser.getId().toString()));


        User actualUpdatedUser =
                UserSteps.getUserByUserName(user.getUsername())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(User.class);

        assertAll(
                () -> assertEquals(
                        updatedUser.getFirstName(),
                        actualUpdatedUser.getFirstName()
                ),
                () -> assertEquals(
                        updatedUser.getPhone()
                        , actualUpdatedUser.getPhone()
                ),
                () -> assertEquals(
                        updatedUser.getEmail()
                        , actualUpdatedUser.getEmail()
                )
        );

        Response logoutResponse = UserSteps.logout();
        logoutResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("ok"));

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
