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

public class UpdatedUser {

    @Epic("PetStore API")
    @Feature("User management")
    @Story("Updated User")
    @DisplayName("Update user")
    @Test
    void updateUser() {
        User user = UserFactory.createRandomUser();
        Response createUser = UserSteps.createUser(user);
        createUser.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(user.getId().toString()));

        User updatedUser = UserFactory.updateRandomUser(user);

        Response updateResponse = UserSteps.updateUser(user.getUsername(), updatedUser);
               updateResponse.then()
                       .statusCode(200)
                       .body("code", equalTo(200))
                       .body("message", equalTo(updatedUser.getId().toString()))
                       .log().body();

        Response getResponse = UserSteps.getUserByUserName(user.getUsername());

            User actualUser = getResponse.as(User.class);

            assertAll(
                    () -> assertEquals(
                            updatedUser.getFirstName(),
                            actualUser.getFirstName()
                    ),
                    () -> assertEquals(
                            updatedUser.getPhone()
                            , actualUser.getPhone()
                    ),
                    () -> assertEquals(
                            updatedUser.getEmail()
                            , actualUser.getEmail()
                    )
            );

    }
}
