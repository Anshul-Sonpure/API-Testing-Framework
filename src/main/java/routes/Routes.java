package routes;

public class Routes {

    // Routes for restapi adequate shop
	public static String baseurl = "http://restapi.adequateshop.com";

    public static String registration_url = baseurl+"/api/authaccount/registration";

    public static String login_url = baseurl+"/api/authaccount/login";

    public static String getUser_url = baseurl+ "/api/users/{userid}";

    public static String createUser_url = baseurl+"/api/users";
    
    
    
    // Routes for gorest.co.in
    public static String gorestbaseurl = "https://gorest.co.in";
    
    public static String post_url = gorestbaseurl + "/public/v2/users/";
    
    public static String get_url = gorestbaseurl+ "/public/v2/users/{id}";
    
    public static String update_url = gorestbaseurl+ "/public/v2/users/{id}";
    
    public static String delete_url = gorestbaseurl+ "/public/v2/users/{id}";
}
