package mobileStoreEndPoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.Products;
import payload.User;
import utilities.ExcelUtil;

import static io.restassured.RestAssured.given;

public class mobilestoreEndPoints {

	static String path = "./src/test/resources/mobilestoreTestData.xlsx";
	static ExcelUtil xl = new ExcelUtil(path);

	static ResourceBundle getUrl() {
		ResourceBundle route = ResourceBundle.getBundle("mobilestore");
		return route;
	}

	public static Response loginUser() throws IOException {

		String email = xl.getCellData("login", 1, 0);
		String password = xl.getCellData("login", 1, 1);
		JSONObject payload = new JSONObject();

		payload.put("email", email);
		payload.put("password", password);
		String loginurl = getUrl().getString("login_url");
		Response response = given().contentType(ContentType.JSON).body(payload.toString()).when().post(loginurl);
		return response;
	}
	
	public static Response registerUser(User payload) throws IOException {

		String regitserurl = getUrl().getString("register_url");
		Response response = given()
				 .header("Content-Type", "application/json")
				 .contentType(ContentType.JSON)
				 .body(payload)
				 .when()
				 .post(regitserurl);
		return response;
	}

	public static Response createProducts(Products payload) throws IOException {
		String posturl = getUrl().getString("post_products");
		Path path = Paths.get("./src/test/resources/loginToken.txt");
		String token = Files.readString(path);
		Response response = given()
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload).when().post(posturl);
		
		return response;

	}
	
	public static Response getProducts(String name) throws IOException {
		String getProducts = getUrl().getString("get_products");
		Path path = Paths.get("./src/test/resources/loginToken.txt");
		String token = Files.readString(path);
		Response response = given()
				.header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json")
				.contentType(ContentType.JSON)
				.param("name",name)
				.get(getProducts);
		return response;

	}
	
	public static Response getProduct(String name) throws IOException {
		String getProduct = getUrl().getString("get_product");
		Path path = Paths.get("./src/test/resources/loginToken.txt");
		String token = Files.readString(path);
		Response response = given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				.param("name",name)
				.get(getProduct);
		return response;

	}

	public static Response updateProduct(Products payload,String id) throws IOException {
		String updateProduct = getUrl().getString("update_products");
		Path path = Paths.get("./src/test/resources/loginToken.txt");
		String token = Files.readString(path);
		Response response = given()
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("id",id)
				.body(payload).when().patch(updateProduct);
		
		return response;

	}
	
	public static Response deleteProduct(String id) throws IOException {
		String deleteProduct = getUrl().getString("delete_products");
		Path path = Paths.get("./src/test/resources/loginToken.txt");
		String token = Files.readString(path);
		Response response = given()
				.header("Authorization", "Bearer " + token)
				.pathParam("id",id)
				.delete(deleteProduct);
		return response;

	}
	
	
}
