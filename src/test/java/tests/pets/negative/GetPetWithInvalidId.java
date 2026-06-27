package tests.pets.negative;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.PetSteps;

import static org.hamcrest.Matchers.*;

public class GetPetWithInvalidId {
    @Epic("PetStore API")
    @Feature("Pet management")
    @Story("Find not existing pet")
    @DisplayName("Get not existing pet by id test")
    @Test
    void getPetWithInvalidIdTest() {
        Response response = PetSteps.getPetByInvalidId("fsfsf");
        response.then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("type", equalTo("unknown"))
                .body("message", containsString("NumberFormatException"));


    }
}
