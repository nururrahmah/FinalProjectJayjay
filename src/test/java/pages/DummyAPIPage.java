package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Map;

public class DummyAPIPage {

    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String APP_ID = "63a804408eb0cb069b57e43a";
    private Response response;
    private final RequestSpecification request;
    private Map<String, String> userData;

    public DummyAPIPage() {
        RestAssured.baseURI = BASE_URL;
        this.request = RestAssured.given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json");
    }

    public void setBaseURL() {
        RestAssured.baseURI = BASE_URL;
    }

    public void setUserData(Map<String, String> userData) {
        this.userData = userData;
    }

    public void sendGetRequest(String endpoint) {
        try {
            response = request.get(endpoint);
            System.out.println("GET Request sent to: " + BASE_URL + endpoint);
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());
        } catch (Exception e) {
            System.err.println("Error sending GET request: " + e.getMessage());
        }
    }

    public void sendPostRequest(String endpoint) {
        try {
            JSONObject requestBody = new JSONObject();
            if (userData != null) {
                userData.forEach(requestBody::put);
            }

            response = request
                    .body(requestBody.toString())
                    .post(endpoint);

            System.out.println("POST Request sent to: " + BASE_URL + endpoint);
            System.out.println("Request Body: " + requestBody.toString());
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());
        } catch (Exception e) {
            System.err.println("Error sending POST request: " + e.getMessage());
        }
    }

    public void sendPutRequest(String endpoint) {
        try {
            JSONObject requestBody = new JSONObject();
            if (userData != null) {
                userData.forEach(requestBody::put);
            }

            response = request
                    .body(requestBody.toString())
                    .put(endpoint);

            System.out.println("PUT Request sent to: " + BASE_URL + endpoint);
            System.out.println("Request Body: " + requestBody.toString());
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());
        } catch (Exception e) {
            System.err.println("Error sending PUT request: " + e.getMessage());
        }
    }

    public int getResponseStatus() {
        return response != null ? response.getStatusCode() : 0;
    }

    public String getResponseBody() {
        return response != null ? response.getBody().asString() : "";
    }

    public boolean responseContainsUsersList() {
        try {
            String responseBody = getResponseBody();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("data") && jsonResponse.get("data") instanceof JSONArray;
        } catch (Exception e) {
            System.err.println("Error checking users list: " + e.getMessage());
            return false;
        }
    }

    public boolean responseContainsUserId(String userId) {
        try {
            String responseBody = getResponseBody();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("id") && jsonResponse.getString("id").equals(userId);
        } catch (Exception e) {
            System.err.println("Error checking user ID: " + e.getMessage());
            return false;
        }
    }

    public boolean responseContainsCreatedUser() {
        try {
            String responseBody = getResponseBody();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("id") &&
                    jsonResponse.has("firstName") &&
                    jsonResponse.has("lastName") &&
                    jsonResponse.has("email");
        } catch (Exception e) {
            System.err.println("Error checking created user: " + e.getMessage());
            return false;
        }
    }

    public boolean responseContainsUpdatedUser() {
        try {
            String responseBody = getResponseBody();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("id") &&
                    (jsonResponse.has("firstName") || jsonResponse.has("lastName"));
        } catch (Exception e) {
            System.err.println("Error checking updated user: " + e.getMessage());
            return false;
        }
    }

    public boolean responseContainsPostsList() {
        try {
            String responseBody = getResponseBody();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("data") && jsonResponse.get("data") instanceof JSONArray;
        } catch (Exception e) {
            System.err.println("Error checking posts list: " + e.getMessage());
            return false;
        }
    }

    public boolean responseContainsError() {
        try {
            String responseBody = getResponseBody();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.has("error") ||
                    responseBody.toLowerCase().contains("error") ||
                    responseBody.toLowerCase().contains("invalid");
        } catch (Exception e) {
            System.err.println("Error checking for error message: " + e.getMessage());
            return false;
        }
    }
}
