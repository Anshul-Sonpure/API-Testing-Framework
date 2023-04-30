package testEndPoints;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adequateShopEndPoints.AdequateEndpoints;
import io.restassured.response.Response;
import payload.User;

public class AdequateEndpointTests {

	
	static Faker faker = new Faker();
    static User userpayload = new User();
   
    public static Logger loger = LogManager.getLogger("adequate");
  
    
    @Test(priority=1)
    public static void TestUserRegister() throws IOException
    {	
    	 
         userpayload.setName(faker.name().fullName());
         userpayload.setEmail(faker.internet().emailAddress());
         userpayload.setPassword(faker.internet().password());
    	
         loger.log(Level.INFO, "*****User Payload Created*****");
        Response response = AdequateEndpoints.registerUser(userpayload);
        loger.log(Level.INFO, "*****Post Request Send to register api*****");
        response.then().log().all().toString();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.body().path("message"), "success");
        User.name = response.body().path("data.Name");
        User.email = response.body().path("data.Email");
        String userId = response.body().path("data.Id").toString();
        String Token = response.body().path("data.Token");
        FileWriter fWriter = new FileWriter("./AdequateData/RegisterUser.txt"); // to append data in file use ",true"
        fWriter.write("UserName:"+User.name+"\nUserEmail:"+User.email+"\nUserId:"+userId+"\nToken:"+Token+"\nPassword:"+User.password+"\n");

        fWriter.close();
      
        loger.log(Level.INFO, "*****User Details Saved to RegisterUser.txt *****");
    }
    
    
    
    @Test(priority=2)
    public void TestValidUserLogin() throws IOException
    {
    	Path path = Paths.get("./AdequateData/RegisterUser.txt");
    	Object[] logindata = Files.readAllLines(path).toArray();
    	
    	userpayload.setEmail(logindata[1].toString().split("UserEmail:")[1]);
    	userpayload.setPassword(logindata[4].toString().split("Password:")[1]);
    	
    	loger.log(Level.INFO, "*****Login User Payload created *****");
    	
    	Response response = AdequateEndpoints.loginUser(userpayload);
    	loger.log(Level.INFO, "*****Login User Payload Send to login api *****");
    	User.name = response.body().path("data.Name");
    	response.then().log().all().toString();
    	Assert.assertEquals(response.statusCode(),200);
    	Assert.assertEquals(response.body().path("message"), "success");
    	Assert.assertEquals(response.body().path("data.Id").toString(),logindata[2].toString().split("UserId:")[1].toString());
    	Assert.assertEquals(response.body().path("data.Name").toString(),logindata[0].toString().split("UserName:")[1].toString());
    	FileWriter fWriter = new FileWriter("./AdequateData/loginUser.txt"); // to append data in file use ",true"
        fWriter.write("UserName:"+User.name+"\nUserEmail:"+User.email+"\nToken:"+response.body().path("data.Token"));

        fWriter.close();
        loger.log(Level.INFO, "*****User Details Saved to loginUser.txt *****");
    }
    
    
    @Test(priority=5)
    public void TestInValidUserLogin()
    {
    	
    	Response response = AdequateEndpoints.loginUser(userpayload);
    	loger.log(Level.INFO, "*****Post Request Send to login User *****");
    	response.then().log().all().toString();
    	Assert.assertEquals(response.statusCode(),200);
    	Assert.assertEquals(response.body().path("message"), "invalid username or password");
    	loger.log(Level.INFO, "*****Validated Invalid User Login *****");
    }
    
    @Test(priority=3)
    public void TestCreateUser() throws IOException
    {
    	userpayload.setName(faker.name().fullName());
    	userpayload.setEmail(faker.internet().emailAddress());
    	userpayload.setLocation(faker.address().cityName());
    	loger.log(Level.INFO, "*****User Payload created for CreateUser *****");
    	Response response = AdequateEndpoints.createUser(userpayload);
    	loger.log(Level.INFO, "*****Post Request Send to createUser *****");
    	response.then().log().all();
    	Assert.assertEquals(response.statusCode(),201);
    	Assert.assertEquals("HTTP/1.1 201 Created",response.statusLine());
    	FileWriter fWriter = new FileWriter("./AdequateData/createUser.txt"); // to append data in file use ",true"
        fWriter.write("Userid:"+response.body().path("id")+"\nUserName:"+User.name+"\nUserEmail:"+User.email+"\nToken:"+response.body().path("data.Token"));

        fWriter.close();
        loger.log(Level.INFO, "*****User Details Saved to createUser *****");
    }
    
    @Test(priority=4)
    public void TestgetUser() throws IOException
    {
    	Path path = Paths.get("./AdequateData/createUser.txt");
    	Object[] userId = Files.readAllLines(path).toArray();
    	String userid = userId[0].toString().split("Userid:")[1];
    	String email = userId[2].toString().split("UserEmail:")[1];
    	Response response = AdequateEndpoints.getUser();
    	Assert.assertEquals(response.statusCode(),200);
    	Assert.assertEquals(response.body().path("id").toString(),userid);
    	Assert.assertEquals(response.body().path("email").toString(),email);
    	 loger.log(Level.INFO, "*****Validated getUser*****");
    }
    
    
      
    
}
