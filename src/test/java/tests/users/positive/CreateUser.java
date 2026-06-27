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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUser {

    @Epic("PetStore API")
    @Feature("User management")
    @Story("Create user")
    @DisplayName("Create new user with valid data")
    @Test
    void CreateUserTest() {
        User user = UserFactory.createRandomUser();
        Response createResponse = UserSteps.createUser(user);

        createResponse.then()
                .statusCode(200)
                .body("message", equalTo(user.getId().toString()));

        Response getResponse = UserSteps.getUserByUserName(user.getUsername());
        getResponse.then()
                        .statusCode(200);
        User actualUser = getResponse.as(User.class);

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
    }
}
