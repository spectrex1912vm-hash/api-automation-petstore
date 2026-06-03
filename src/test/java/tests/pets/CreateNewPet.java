package tests.pets;

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
import static org.hamcrest.Matchers.notNullValue;

public class CreateNewPet {
    @Epic("PetStore API")
    @Feature("Pet management")
    @Story("Create and get a new pet")
    @DisplayName("Create and get pet")
    @Test
    void createAndGetNewPet() {
        Pet pet = PetFactory.createRandomPet();
               Response createResponse = PetSteps.createPet(pet);

                        createResponse.then()
                        .statusCode(200)
                        .body("id", notNullValue())
                        .body("name", equalTo(pet.getName()))
                        .body("status", equalTo(pet.getStatus()));

               Long id = createResponse.jsonPath().getLong("id");

               Response getResponse = PetSteps.getPetById(id);

               getResponse.then()
                       .statusCode(200)
                       .body("id", equalTo(id.intValue()))
                       .body("name", equalTo(pet.getName()))
                       .body("status", equalTo(pet.getStatus()));

    }
}
