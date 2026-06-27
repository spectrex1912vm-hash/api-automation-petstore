package tests.pets.positive;

import factories.PetFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.pet.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.PetSteps;

import static org.hamcrest.Matchers.equalTo;

public class DeletePet {
    @Epic("PetStore API")
    @Feature("Pet management")
    @Story("Delete pet from store")
    @DisplayName("Delete pet")
    @Test
    void deletePet() {
        Pet pet = PetFactory.createRandomPet();
        Response createResponse = PetSteps.createPet(pet);
        Long id = createResponse.jsonPath().getLong("id");

        Response deleteResponse = PetSteps.deletePet(id);
        deleteResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(String.valueOf(id)));

        PetSteps.getPetById(id)
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }
}
