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

import static org.hamcrest.Matchers.greaterThan;

public class CreatePetAndFindByStatus {
    @Epic("PetStore API")
    @Feature("Pet management")
    @Story("Create and find pet by status")
    @DisplayName("Find pet by status")
    @Test
    void getPetByStatusTest() {
        Pet pet = PetFactory.createRandomPet();
        Response createResponse = PetSteps.createPet(pet);

        Long id = createResponse.jsonPath().getLong("id");

        Response response = PetSteps.getPetByStatus(pet.getStatus());

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("findAll{ it.id == " + id + "}.size()", greaterThan(0));

    }
}
