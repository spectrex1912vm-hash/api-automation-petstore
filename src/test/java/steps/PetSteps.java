package steps;

import io.restassured.response.Response;
import models.pet.Pet;
import specs.Specs;

import static io.restassured.RestAssured.given;

public class PetSteps {
    public static Response createPet(Pet pet) {
        return given()
                .spec(Specs.request)
                .body(pet)
                .when()
                .post("/pet");
    }

    public static Response getPetById(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/pet/" + id);
    }

    public static Response getPetByStatus(String status) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/pet/findByStatus?status=" + status);
    }

    public static Response updatePet(Pet pet) {
        return given()
                .spec(Specs.request)
                .body(pet)
                .when()
                .put("/pet");
    }

    public static Response deletePet(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .delete("/pet/" + id);
    }
}
