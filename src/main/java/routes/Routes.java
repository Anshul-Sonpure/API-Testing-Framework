package routes;

public class Routes {

    // Routes for restapi adequate shop
	public static String baseurl = "http://restapi.adequateshop.com";

    public static String registration_url = baseurl+"/api/authaccount/registration";

    public static String login_url = baseurl+"/api/authaccount/login";

    public static String getUser_url = baseurl+ "/api/users/{userid}";

    public static String createUser_url = baseurl+"/api/users";
    
   
}
