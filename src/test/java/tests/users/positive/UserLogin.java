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
import static org.hamcrest.Matchers.*;


public class UserLogin {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Login user with query params")
    @DisplayName("Login user")
    @Test
    void loginTest (){
        User user = UserFactory.createRandomUser();

        Response createResponse = UserSteps.createUser(user);

        createResponse.then()
                        .statusCode(200);

        Response loginResponsw = UserSteps.login(user);

        loginResponsw.then()
                .body("code", equalTo(200))
                .body("message", containsString("logged in user session"));

    }
}
