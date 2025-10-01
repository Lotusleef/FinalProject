package api.stepdef;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiStepDef {

    private final ApiWorld world;

    public ApiStepDef(ApiWorld world) {
        this.world = world;
    }

    @Given("API base is DummyAPI")
    public void api_base_is_dummyapi() {
        RestAssured.baseURI = world.baseUrl;
    }

    @When("I get user by id {string} @api")
    public void i_get_user_by_id(String id) {
        world.lastResponse = RestAssured.given()
                .header("app-id", world.appId)
                .when()
                .get("/user/{id}", id)
                .then()
                .extract().response();
    }

    @When("I create a new user @api")
    public void i_create_a_new_user() {
        String uniqueEmail = "user_" + UUID.randomUUID() + "@example.com";

        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Ray");
        body.put("lastName", "Han");
        body.put("email", uniqueEmail);

        Response res = RestAssured.given()
                .header("app-id", world.appId)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/user/create")
                .then()
                .extract().response();

        world.lastResponse = res;
        if (res.jsonPath().get("id") != null) {
            world.createdUserId = res.jsonPath().getString("id");
        }
    }

    @When("I update the user with new firstName {string} @api")
    public void i_update_the_user(String newFirstName) {
        assertThat(world.createdUserId).as("User must be created first").isNotBlank();

        Map<String, Object> body = new HashMap<>();
        body.put("firstName", newFirstName);

        world.lastResponse = RestAssured.given()
                .header("app-id", world.appId)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/user/{id}", world.createdUserId)
                .then()
                .extract().response();
    }

    @When("I delete the user @api")
    public void i_delete_the_user() {
        assertThat(world.createdUserId).as("User must be created first").isNotBlank();

        world.lastResponse = RestAssured.given()
                .header("app-id", world.appId)
                .when()
                .delete("/user/{id}", world.createdUserId)
                .then()
                .extract().response();
    }

    @When("I get tag list @api")
    public void i_get_tag_list() {
        world.lastResponse = RestAssured.given()
                .header("app-id", world.appId)
                .when()
                .get("/tag")
                .then()
                .extract().response();
    }

    @Then("response status should be {int}")
    public void response_status_should_be(Integer code) {
        assertThat(world.lastResponse.getStatusCode()).isEqualTo(code);
    }

    @Then("response should contain field {string}")
    public void response_should_contain_field(String field) {
        assertThat(world.lastResponse.jsonPath().getString(field)).isNotBlank();
    }

    @Then("response list size {string} should be > {int}")
    public void response_list_size_should_be_gt(String field, int min) {
        int size = world.lastResponse.jsonPath().getList(field).size();
        assertThat(size).isGreaterThan(min);
    }

    @When("I pick an existing user id from list @api")
    public void i_pick_existing_user_id_from_list() {
        world.lastResponse = RestAssured.given()
                .header("app-id", world.appId)
                .when()
                .get("/user?limit=1")
                .then()
                .extract().response();

        String id = world.lastResponse.jsonPath().getString("data[0].id");
        org.assertj.core.api.Assertions.assertThat(id).isNotBlank();
        world.createdUserId = id; // reuse slot untuk simpan id
    }

    @When("I get user by that id @api")
    public void i_get_user_by_that_id() {
        world.lastResponse = RestAssured.given()
                .header("app-id", world.appId)
                .when()
                .get("/user/{id}", world.createdUserId)
                .then()
                .extract().response();
    }

}
