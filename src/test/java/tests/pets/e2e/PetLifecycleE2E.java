package tests.pets.e2e;

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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetLifecycleE2E {
    @Epic("PetStore API")
    @Feature("Pet management")
    @Story("Pet lifecycle")
    @DisplayName("Pet e2e test")
    @Test
    void petLifecycleFullTest() {
        Pet pet = PetFactory.createRandomPet();
        Response createResponse = PetSteps.createPet(pet);
        createResponse.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));


        Pet actualPet =
                PetSteps.getPetById(pet.getId())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Pet.class);
        assertAll(
                () -> assertEquals(pet.getId(), actualPet.getId()),
                () -> assertEquals(pet.getName(), actualPet.getName()),
                () -> assertEquals(pet.getStatus(), actualPet.getStatus())
        );

        Pet updatedPet = PetFactory.updateRandomPet(pet);

        Response updateResponse = PetSteps.updatePet(updatedPet);
        updateResponse.then()
                .statusCode(200)
                .body("id",equalTo(pet.getId().intValue()))
                .body("name", equalTo(updatedPet.getName()))
                .body("status", equalTo(updatedPet.getStatus()));

        Pet actualUpdatedPet =
                PetSteps.getPetById(pet.getId())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Pet.class);
        assertAll(
                () -> assertEquals(updatedPet.getId(), actualUpdatedPet.getId()),
                () -> assertEquals(updatedPet.getName(), actualUpdatedPet.getName()),
                () -> assertEquals(updatedPet.getStatus(), actualUpdatedPet.getStatus())

        );



        Response deleteResponse = PetSteps.deletePet(pet.getId());
        deleteResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(String.valueOf(pet.getId())));

        PetSteps.getPetById(pet.getId())
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }
}
