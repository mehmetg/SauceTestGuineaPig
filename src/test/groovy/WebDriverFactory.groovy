package Tests;

import Pages.GuineaPigPage;
import com.saucelabs.common.SauceOnDemandAuthentication;

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

    public static WebDriver getSauceDriver(String os, String version, String browser, String deviceName, String deviceOrientation) {

        String username = "mehmetg";
        //System.getenv("SAUCE_USER_NAME") != null ? System.getenv("SAUCE_USER_NAME") : System.getenv("SAUCE_USERNAME");
        String accesskey = "99eb74db-41af-425f-a6a2-23fb5c7c84bb";
        //System.getenv("SAUCE_API_KEY") != null ? System.getenv("SAUCE_API_KEY") : System.getenv("SAUCE_ACCESS_KEY");

        SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser != null) capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) capabilities.setCapability(CapabilityType.VERSION, version);
        if (deviceName != null) capabilities.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) capabilities.setCapability("device-orientation", deviceOrientation);

        capabilities.setCapability(CapabilityType.PLATFORM, os);

        WebDriver driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                        "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
    }


    public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();

        // windows 7, Chrome 41
        browsers.add(["Windows 7", "41", "chrome", null, null] as String[]);
        /*
        // windows xp, IE 8
        browsers.add(["Windows XP", "8", "internet explorer", null, null] as String[]);

        // windows 7, IE 9
        browsers.add(["Windows 7", "9", "internet explorer", null, null] as String[]);

        // windows 8, IE 10
        browsers.add(["Windows 8", "10", "internet explorer", null, null] as String[]);

        // windows 8.1, IE 11
        browsers.add(["Windows 8.1", "11", "internet explorer", null, null] as String[]);

        // OS X 10.8, Safari 6
        browsers.add(["OSX 10.8", "6", "safari", null, null] as String[]);

        // OS X 10.9, Safari 7
        browsers.add(["OSX 10.9", "7", "safari", null, null] as String[]);

        // OS X 10.10, Safari 7
        browsers.add(["OSX 10.10", "8", "safari", null, null] as String[]);

        // Linux, Firefox 37
        browsers.add(["Linux", "37", "firefox", null, null] as String[]);
/       */

        // This could define the number of overall threads running. (numClasses * numThreads)
        // int numTestClasses = browsers.size();
        // int numMaxTestThreads = Integer.parseInt(System.getProperty('junit.parallel.threads','1'))
        // numMaxTestThreads = Math.max((int)(numMaxTestThreads/numTestClasses), 1);
        // System.setProperty('junit.parallel.threads', numMaxTestThreads.toString());

        return browsers;
    }
}