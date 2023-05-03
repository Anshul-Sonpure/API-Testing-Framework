package testEndPoints;
import java.io.IOException;

import org.testng.annotations.Test;

import goRestUser.GoRestEndPoint;
import io.restassured.response.Response;

public class GoRestEndPointTest {

    @Test
    public void testGetRequest() throws IOException {
        String endpoint = "https://gorest.co.in/public/v2/users/";
        String httpMethod = "GET";
        Response response = GoRestEndPoint.sendRequest(endpoint, httpMethod, null);
        response.then().log().all();
        
        // Assert response
    }

    @Test
    public void testPostRequest() throws IOException {
        String endpoint = "https://gorest.co.in/public/v2/users/";
        String httpMethod = "POST";
        String payload = "{\r\n"
        		
        		+ "    \"name\": \"Vasu Embran\",\r\n"
        		+ "    \"email\": \"embran_vasudev312@koepp-adams.test\",\r\n"
        		+ "    \"gender\": \"female\",\r\n"
        		+ "    \"status\": \"active\"\r\n"
        		+ "}";
        Response response = GoRestEndPoint.sendRequest(endpoint, httpMethod, payload);
        response.then().log().all();
    }

}
