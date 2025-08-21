package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import pages.DummyAPIPage;
import org.junit.Assert;
import java.util.Map;
import java.util.List;

public class DummyAPIStepDefinitions {

    private DummyAPIPage apiPage;
    private Map<String, String> userData;

    public DummyAPIStepDefinitions() {
        this.apiPage = new DummyAPIPage();
    }

    @Given("the DummyAPI base URL is set")
    public void the_dummy_api_base_url_is_set() {
        apiPage.setBaseURL();
    }

    @Given("I have valid user data:")
    public void i_have_valid_user_data(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        this.userData = new java.util.HashMap<>(data.get(0));
        String uniqueEmail = "john.doe" + System.currentTimeMillis() + "@test.com";
        this.userData.put("email", uniqueEmail);
        apiPage.setUserData(userData);
    }

    @Given("I have user update data:")
    public void i_have_user_update_data(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        this.userData = data.get(0);
        apiPage.setUserData(userData);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        apiPage.sendGetRequest(endpoint);
    }

    @When("I send a POST request to {string} with the user data")
    public void i_send_a_post_request_to_with_the_user_data(String endpoint) {
        apiPage.sendPostRequest(endpoint);
    }

    @When("I send a PUT request to {string} with the update data")
    public void i_send_a_put_request_to_with_the_update_data(String endpoint) {
        apiPage.sendPutRequest(endpoint);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int expectedStatus) {
        int actualStatus = apiPage.getResponseStatus();
        Assert.assertEquals("Response status mismatch", expectedStatus, actualStatus);
    }

    @Then("the response should contain a list of users")
    public void the_response_should_contain_a_list_of_users() {
        Assert.assertTrue("Response should contain users data",
                apiPage.responseContainsUsersList());
    }

    @Then("the response should contain user details with id {string}")
    public void the_response_should_contain_user_details_with_id(String userId) {
        Assert.assertTrue("Response should contain user with specific ID",
                apiPage.responseContainsUserId(userId));
    }

    @Then("the response should contain the created user details")
    public void the_response_should_contain_the_created_user_details() {
        Assert.assertTrue("Response should contain created user details",
                apiPage.responseContainsCreatedUser());
    }

    @Then("the response should contain the updated user information")
    public void the_response_should_contain_the_updated_user_information() {
        Assert.assertTrue("Response should contain updated user information",
                apiPage.responseContainsUpdatedUser());
    }

    @Then("the response should contain a list of posts")
    public void the_response_should_contain_a_list_of_posts() {
        Assert.assertTrue("Response should contain posts data",
                apiPage.responseContainsPostsList());
    }

    @Then("the response should contain an error message")
    public void the_response_should_contain_an_error_message() {
        Assert.assertTrue("Response should contain error message",
                apiPage.responseContainsError());
    }
}