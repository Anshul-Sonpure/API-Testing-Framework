package goRestUser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestEndPoint {

    public static Response sendRequest(String endpoint, String httpMethod, Object payload) throws IOException {

        RestAssured.baseURI = "https://gorest.co.in"; //replace with your API base URL
        Path path = Paths.get("./src/main/java/goRestUser/token.txt");
		String token = Files.readString(path);
        Response response = null;
        switch (httpMethod) {
            case "GET":
                response = RestAssured.given()
                        .when()
                        .get(endpoint)
                        .then()
                        .extract().response();
                break;
            case "POST":
                response = RestAssured.given()
                		 .header("Content-Type", "application/json")
                		 .header("Authorization", "Bearer " + token)
         				.contentType(ContentType.JSON)
         				.accept(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post(endpoint)
                        .then()
                        .extract().response();
                break;
            case "PATCH":
                response = RestAssured.given()
                		 .header("Content-Type", "application/json")
                		 .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .patch(endpoint)
                        .then()
                        .extract().response();
                break;
            case "DELETE":
                response = RestAssured.given()
                		 .header("Content-Type", "application/json")
                		 .header("Authorization", "Bearer " + token)
                        .when()
                        .delete(endpoint)
                        .then()
                        .extract().response();
                break;
            default:
                System.out.println("Unsupported HTTP method");
                break;
        }
        return response;
    }
}
