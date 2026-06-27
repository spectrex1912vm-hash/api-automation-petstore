package tests.pets.negative;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.PetSteps;

public class DeletePetWithInvalidId {
    @Epic("PetStore API")
    @Feature("Pet management")
    @Story("Delete pet with invalid id")
    @DisplayName("Delete pet with invalid id test")
    @Test
    void deletePetWithInvalidIdTest() {
        Long petId = 43_643_636L;
        Response response = PetSteps.deletePet(petId);
        response.then()
                .statusCode(404);

    }
}
