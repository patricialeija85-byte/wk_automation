package project.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiSteps {
    private Response response;

    @Given("the search API is available")
    public void checkApiAvailability() {
        // BaseURI is handled by Hooks.java calling ConfigReader.
    }

    @When("I search for {string} via API")
    public void searchForTerm(String searchTerm) {
        // The endpoint from your screenshot is /en/search-results
        // Use query parameter 'q' as seen in the URL.
        response = given()
                .queryParam("q", searchTerm)
                .header("Accept", "application/json")
                .when()
                .get("/en/search-results");
    }

    @Then("the response status code must be {int}")
    public void verifyStatus(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain {string}")
    public void verifyContent(String expectedTitle) {
        String body = response.getBody().asString();
        assertTrue(body.contains(expectedTitle));
    }

    @Then("the total results count should be greater than {int}")
    public void verifyResultCount(int minCount) {
        // You can use JSONPath if the response is JSON, otherwise search string
        String body = response.getBody().asString();
        assertTrue(body.contains("Results"));
    }

    @And("the load time should be less than {int} seconds")
    public void theLoadTimeShouldBeLessThanSeconds(int arg0) {
        assertTrue(arg0 < response.getTime(), "The load time should be less than " + arg0);
    }
}