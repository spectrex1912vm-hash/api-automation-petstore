package tests.store.negative;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StoreSteps;

import static org.hamcrest.Matchers.equalTo;

public class DeleteNotExistingOrder {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Delete not existing order")
    @DisplayName("Delete not existing order test")
    @Test
    void deleteNotExistingOrderTest() {
        Long id = 43_643_636L;

        Response deleteResponse = StoreSteps.deleteOrder(id);
        deleteResponse.then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", equalTo("Order Not Found"));


    }
}
