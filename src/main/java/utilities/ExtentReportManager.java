package utilities;

//Extent report 5.x

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener
{
	
	 public static String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss").format(new Date());

	    public static ExtentReports extent;
	    public static ExtentSparkReporter spark;
	    public static ExtentTest test;
	
	
	
	public void onStart(ITestResult result)
	{		
		extent = new ExtentReports();
        spark = new ExtentSparkReporter("test-output/Reports/extentreport_" + timeStamp + ".html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("JVM", System.getProperty("java.runtime.version"));
        extent.setSystemInfo("User Directory", System.getProperty("user.dir"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        
        String classname = result.getTestClass().getName();
        classname=classname.replace("testSauceDemo.","");
        test = extent.createTest(classname)
                 .createNode(result.getMethod().getMethodName())
                 .assignAuthor(System.getProperty("user.name"));
        
        
	}
	
		
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName()); 
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); 
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}
