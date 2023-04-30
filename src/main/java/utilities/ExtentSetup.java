package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentSetup {
	
	 public static String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss").format(new Date());

	    public static ExtentReports extent;
	    public static ExtentSparkReporter spark;
	    
	    
	    public static ExtentReports initExtent() {
	    	 extent = new ExtentReports();
	         spark = new ExtentSparkReporter("test-output/Reports/extentreport_" + timeStamp + ".html");
	         extent.attachReporter(spark);
	         extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	         extent.setSystemInfo("JVM", System.getProperty("java.runtime.version"));
	         extent.setSystemInfo("User Directory", System.getProperty("user.dir"));
	         extent.setSystemInfo("User", System.getProperty("user.name"));
	         return extent;
	    	
	    }

}
