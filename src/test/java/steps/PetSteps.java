package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.pet.Pet;
import specs.Specs;

import static io.restassured.RestAssured.given;

public class PetSteps {
    @Step("Create pet")
    public static Response createPet(Pet pet) {
        return given()
                .spec(Specs.request)
                .body(pet)
                .when()
                .post("/pet");
    }


    public static Response getPetByInvalidId(String id) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/pet/" + id);

    }

    @Step("Get pet by id")
    public static Response getPetById(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/pet/" + id);
    }

    @Step("Get pet by status")
    public static Response getPetByStatus(String status) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/pet/findByStatus?status=" + status);
    }

    @Step("Update pet")
    public static Response updatePet(Pet pet) {
        return given()
                .spec(Specs.request)
                .body(pet)
                .when()
                .put("/pet");
    }

    @Step("Delete pet")
    public static Response deletePet(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .delete("/pet/" + id);
    }
}
