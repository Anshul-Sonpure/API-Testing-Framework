package testEndPoints;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.Faker;

import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import mobileStoreEndPoints.mobilestoreEndPoints;
import payload.Products;
import payload.User;
import utilities.DataProviders;
import utilities.ListenerTest;

public class MobileStoreEndPointTests extends ListenerTest{

	static Faker faker = new Faker();
	static User userpayload = new User();
	public static Logger loger = LogManager.getLogger("mobilestore");
	public static String lineSeparator = System.getProperty("line.separator");
	public static Products prod = new Products();

	@Test(priority = 1)
	public void testLoginUser() throws IOException {
		loger.log(Level.INFO, "*****testLoginUser*****",lineSeparator);
		Response response = mobilestoreEndPoints.loginUser();
		response.then().log().all();
		response.then().statusCode(200);
		loger.log(Level.INFO, "*****Validated Status Code for Login User *****", lineSeparator);
		String Token = response.body().path("access_token");

		FileWriter fWriter = new FileWriter("./src/test/resources/loginToken.txt"); // to append data in file use",true"
																					
		fWriter.write(Token);
		fWriter.close();
		loger.log(Level.INFO, response.headers().toString(),lineSeparator);
		loger.log(Level.INFO, response.body().asPrettyString(),lineSeparator);
		loger.log(Level.INFO, "*****Login Token for Saved at /src/test/resources/loginToken.txt*****", lineSeparator);

		
        
        
		
	}

	@Test(priority = 2)
	public void testRegisterUser() throws IOException {
		loger.log(Level.INFO, "*****testRegisterUser*****");
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		loger.log(Level.INFO, "*****User Payload Created *****");
		
		
		
		Response response = mobilestoreEndPoints.registerUser(userpayload);
		response.then().log().all();
		response.then().statusCode(200);
		loger.log(Level.INFO, "*****Validated Status Code for register User*****");
		String Token = response.body().path("access_token");

		FileWriter fWriter = new FileWriter("./src/test/resources/registerToken.txt"); // to append data in file use",true"
																						
		fWriter.write(Token);
		fWriter.close();
		loger.log(Level.INFO, response.headers().toString(),lineSeparator);
		loger.log(Level.INFO, response.body().asPrettyString(),lineSeparator);
		loger.log(Level.INFO, "*****Login Token for Saved at /src/test/resources/registerToken.txt*****");
		extentTest.info("Request body:"+userpayload);
       
		
		extentTest.info("Response body:");
        extentTest.log(Status.PASS,MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
        
        
	}

	
	@Test(dataProvider="Data", dataProviderClass=DataProviders.class,dependsOnMethods = {"testLoginUser"},priority=3)
	public void testCreateProducts(String name,String cost,String quantity,String locationId,String DealerId) throws IOException
	{
		loger.log(Level.INFO, "*****testCreateProducts*****",lineSeparator);
		
		prod.setName(name);
		prod.setCost(cost);
		prod.setQuantity(quantity);
		prod.setLocationId(locationId);
		prod.setDealerId(DealerId);
		
		Response response = mobilestoreEndPoints.createProducts(prod);
		Awaitility.await().atMost(Duration.ONE_MINUTE).pollInterval(Duration.FIVE_SECONDS)
        .until(() -> response.statusCode() == 201);
		
		response.then().log().all();
		loger.log(Level.INFO, "*****Validated Status Code for create Products *****", lineSeparator);
		loger.log(Level.INFO, response.body().asPrettyString(),lineSeparator);
		
		extentTest.info("Request Body send:\n"+prod);
		
		extentTest.info("Response body:");
        extentTest.log(Status.PASS,MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
		
	}
	
	

	@Test(dataProvider="Names", dataProviderClass=DataProviders.class,dependsOnMethods = {"testLoginUser","testCreateProducts"},priority=4)
	public void testGetProducts(String name) throws IOException
	{
		loger.log(Level.INFO, "*****testGetProducts*****",lineSeparator);
		Response response = mobilestoreEndPoints.getProducts(name);
		response.then().assertThat().body("name",hasItems(name));
		response.then().log().all();
		loger.log(Level.INFO, "*****Validated Products has name given by Data Prodiver *****", lineSeparator);
		loger.log(Level.INFO, response.body().asPrettyString(),lineSeparator);
		
		extentTest.info("Request Body send:\n"+name);
		
		extentTest.info("Response body:");
        extentTest.log(Status.PASS,MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
	}
	
	
	@Test(dependsOnMethods = {"testLoginUser"},priority=5)
	public void testGetProduct() throws IOException
	{
		loger.log(Level.INFO, "*****testGetProduct*****",lineSeparator);
		String name = "Redmi 11 Prime 5G";
		Response response = mobilestoreEndPoints.getProduct(name);
		response.then().statusCode(200);
		loger.log(Level.INFO, "*****Validated Status Code for GetProduct*****",lineSeparator);
		loger.log(Level.INFO, response.body().asPrettyString(),lineSeparator);
		
		extentTest.info("Product name given:"+name);
		
		extentTest.info("Response body:");
        extentTest.log(Status.PASS,MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
	}
	
	
	
	
	
	@Test(dependsOnMethods = {"testLoginUser"},priority=6)
	public void testUpdateProduct() throws IOException
	{
		loger.log(Level.INFO, "*****testUpdateProduct*****",lineSeparator);
		String id ="6";
		prod.setQuantity("250");
		prod.setDealerId("1");
		Response response = mobilestoreEndPoints.updateProduct(prod, id);
		response.then().statusCode(200);
		loger.log(Level.INFO, "*****Validated Status Code for UpdateProduct*****",lineSeparator);
		loger.log(Level.INFO, response.headers().toString(),lineSeparator);
		loger.log(Level.INFO,"======Response Body====",response.body().asString(), lineSeparator);
		extentTest.info("Product id given to update:"+id);
		
		extentTest.info("Response body:");
        extentTest.log(Status.PASS,MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
	}
	
	
	@Test(dependsOnMethods = {"testLoginUser"},priority=7)
	public void testDeleteProduct() throws IOException
	{
		loger.log(Level.INFO, "*****testDeleteProduct*****",lineSeparator);
		String id = "7";
		Response response = mobilestoreEndPoints.deleteProduct(id);
		response.then().statusCode(200);
		loger.log(Level.INFO, "*****Validated Status Code for deleteProduct*****",lineSeparator);
		loger.log(Level.INFO, response.headers().toString(),lineSeparator);
		loger.log(Level.INFO,"======Response Body====",response.body().asString(), lineSeparator);
		extentTest.info("Product id given to delete:"+id);
		
		extentTest.info("Response body:");
        extentTest.log(Status.PASS,MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
	}
	
	
	    
	

}
