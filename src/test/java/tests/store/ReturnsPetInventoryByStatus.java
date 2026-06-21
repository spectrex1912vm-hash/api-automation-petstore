package tests.store;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StoreSteps;

import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReturnsPetInventoryByStatus {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Returns a map of status codes to quantities")
    @DisplayName("Returns Pet Inventory By Status Test")
    @Test
    void returnsPetInventory () {
        Response response = StoreSteps.getPetInventory();
        response.then()
                .statusCode(200);
        Map<String, Integer>inventory = response.as(new TypeRef<Map<String, Integer>>() {
        });
        assertFalse(inventory.isEmpty());
        assertTrue(
                inventory.values()
                        .stream()
                        .allMatch(count -> count >= 0)

        );
    }
}
