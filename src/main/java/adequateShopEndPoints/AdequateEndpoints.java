package adequateShopEndPoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;
import routes.Routes;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class AdequateEndpoints {

	public static Response registerUser(User payload) {
		Response response = given().contentType(ContentType.JSON).body(payload).when().post(Routes.registration_url);
		return response;
	}

	public static Response loginUser(User payload) {
		Response response = given().contentType(ContentType.JSON).body(payload).when().post(Routes.login_url);
		return response;
	}

	public static Response createUser(User payload) throws IOException {
		Path path = Paths.get("./AdequateData/loginUser.txt");
		Object[] token = Files.readAllLines(path).toArray();
		String Token = token[2].toString().split("Token:")[1];

		Response response = given().header("Authorization", "Bearer " + Token)
				.header("Content-Type", "application/json").contentType(ContentType.JSON).body(payload).when()
				.post(Routes.createUser_url);
		return response;
	}

	public static Response getUser() throws IOException {
		Path path = Paths.get("./AdequateData/createUser.txt");
		Object[] userId = Files.readAllLines(path).toArray();
		String userid = userId[0].toString().split("Userid:")[1];
		path = Paths.get("./AdequateData/loginUser.txt");
		Object[] token = Files.readAllLines(path).toArray();
		String Token = token[2].toString().split("Token:")[1];

		Response response = given().header("Authorization", "Bearer " + Token)
				.header("Content-Type", "application/json").pathParam("userid", userid).when().get(Routes.getUser_url);
		return response;
	}

}
