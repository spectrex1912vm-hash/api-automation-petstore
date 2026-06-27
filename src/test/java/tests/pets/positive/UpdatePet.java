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

public class UpdatePet {

    @Epic("PetStore API")
    @Feature("Pet management")
    @Story("Update and check pet by id")
    @DisplayName("Update pet")
    @Test
    void updatePet() {
        Pet pet = PetFactory.createRandomPet();
        Response createResponse = PetSteps.createPet(pet);
        Long id = createResponse.jsonPath().getLong("id");

        pet.setId(id);
        pet.setName("updated name");
        pet.setStatus("sold");

        Response updateResponse = PetSteps.updatePet(pet);
        updateResponse.then()
                .statusCode(200)
                .body("id",equalTo(id.intValue()))
                .body("name", equalTo("updated name"))
                .body("status", equalTo("sold"));

        PetSteps.getPetById(id)
                .then()
                .statusCode(200)
                .body("name", equalTo("updated name"))
                .body("status", equalTo("sold"));


    }
}
