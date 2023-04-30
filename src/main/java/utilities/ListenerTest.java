package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class ListenerTest implements ITestListener {
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentReports extent = ExtentSetup.initExtent();
    public static ExtentTest extentTest;
    public static String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss").format(new Date());
    public static String screenshot;
    
    @Override
    public void onTestStart(ITestResult result) {
       
        String classname = result.getTestClass().getName();
        String method = result.getMethod().getMethodName();
        classname=classname.replace("testEndPoints.","");
         extentTest = extent.createTest(classname+method)
                 .createNode(result.getMethod().getMethodName())
                 .assignAuthor(System.getProperty("user.name"));
         test.set(extentTest);
         
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS,"Test Case: "+result.getMethod().getMethodName()+ " is passed.");
        
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        test.get().log(Status.FAIL,"Test Case: "+result.getMethod().getMethodName()+ " is failed.");
        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
        test.get().log(Status.SKIP,"Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
     
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
    
    

}
