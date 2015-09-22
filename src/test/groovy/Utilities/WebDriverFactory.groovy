package Utilities;

import Pages.GuineaPigPage;
import com.saucelabs.common.SauceOnDemandAuthentication
import com.saucelabs.saucerest.SauceREST
import gherkin.formatter.model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

import static org.junit.Assert.*;

/**
 * Created by mehmetgerceker on 9/20/15.
 */
public class WebDriverFactory {

    private static SauceREST client = null;
    private static String username = "mehmetg";
    //System.getenv("SAUCE_USER_NAME") != null ? System.getenv("SAUCE_USER_NAME") : System.getenv("SAUCE_USERNAME");
    private static String accesskey = "99eb74db-41af-425f-a6a2-23fb5c7c84bb";
    //System.getenv("SAUCE_API_KEY") != null ? System.getenv("SAUCE_API_KEY") : System.getenv("SAUCE_ACCESS_KEY");

    private static SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey);

    public static WebDriver getSauceDriver(String os, String version, String browser, String deviceName, String deviceOrientation, String testName) {



        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser != null) capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) capabilities.setCapability(CapabilityType.VERSION, version);
        if (deviceName != null) capabilities.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) capabilities.setCapability("device-orientation", deviceOrientation);

        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability('name', testName);
        WebDriver driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                        "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
    }

    public static SauceREST getSauceRESTClient(){
        if (client == null){
            client = new SauceREST(username, accesskey);
        }
        return client;
    }

    public static void markPassFail(String sessionId, ArrayList<Result> results){
        Map<String, Object> jobInfo = new HashMap<String, Object>();
        boolean result = null;
        for(Result r:results){
            result = r.status == "passed";
            if (!result)
                break;
        }
        jobInfo.put('passed', result);
        getSauceRESTClient().updateJobInfo(sessionId, jobInfo);
   }
}