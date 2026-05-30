package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class Specs {
    public static RequestSpecification request = with()
            .filter(new AllureRestAssured())
            .log().uri()
            .log().body()
            .baseUri("https://petstore.swagger.io")
            .basePath("/v2")
            .contentType(JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();


}
