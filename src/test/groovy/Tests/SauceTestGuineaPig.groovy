package Tests;

import Pages.GuineaPigPage
import com.saucelabs.common.SauceOnDemandAuthentication;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher

import static org.junit.Assert.*;

import com.saucelabs.common.SauceOnDemandSessionIdProvider

/**
 * Created by mehmetgerceker on 9/20/15.
 */
@RunWith(ConcurrentParameterized.class)
public class SauceTestGuineaPig implements SauceOnDemandSessionIdProvider {

    static final String GUINEA_PIG_URL = "https://saucelabs.com/test/guinea-pig";

    private GuineaPigPage testPage;

    public String username = System.getenv("SAUCE_USER_NAME") != null ? System.getenv("SAUCE_USER_NAME") : "";
    public String accesskey = System.getenv("SAUCE_API_KEY") != null ? System.getenv("SAUCE_API_KEY") : "";

    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey);

    /**
     * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
     */
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    @Rule public TestName name = new TestName() {
        public String getMethodName() {
            return String.format("%s : (%s %s %s)", super.getMethodName(), os, browser, version);
        };
    };

    /**
     * Represents the browser to be used as part of the test run.
     */
    private String browser;
    /**
     * Represents the operating system to be used as part of the test run.
     */
    private String os;
    /**
     * Represents the version of the browser to be used as part of the test run.
     */
    private String version;
    /**
     * Represents the deviceName of mobile device
     */
    private String deviceName;
    /**
     * Represents the device-orientation of mobile device
     */
    private String deviceOrientation;
    /**
     * Instance variable which contains the Sauce Job Id.
     */
    private String sessionId;

    /**
     * The {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private WebDriver driver;

    /**
     * Constructs a new instance of the test.  The constructor requires three string parameters, which represent the operating
     * system, version and browser to be used when launching a Sauce VM.  The order of the parameters should be the same
     * as that of the elements within the {@link #browsersStrings()} method.
     * @param os
     * @param version
     * @param browser
     * @param deviceName
     * @param deviceOrientation
     */

    public SauceTestGuineaPig(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
        this.deviceName = deviceName;
        this.deviceOrientation = deviceOrientation;
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the {@link #browser},
     * {@link #version} and {@link #os} instance variables, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @throws Exception if an error occurs during the creation of the {@link RemoteWebDriver} instance.
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser != null) capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) capabilities.setCapability(CapabilityType.VERSION, version);
        if (deviceName != null) capabilities.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) capabilities.setCapability("device-orientation", deviceOrientation);

        capabilities.setCapability(CapabilityType.PLATFORM, os);

        String methodName = name.getMethodName();
        capabilities.setCapability("name", methodName);

        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                        "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

        String message = "SauceOnDemandSessionID=" + this.sessionId + " job-name=" + methodName;
        System.out.println(message);
        this.testPage = GuineaPigPage.navigateTo(this.driver, GUINEA_PIG_URL);
    }

    /**
     * Closes the {@link WebDriver} session.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     *
     * @return the value of the Sauce Job id.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @return a LinkedList containing String arrays representing the browser combinations the test should be run against. The values
     * in the String array are used as part of the invocation of the test constructor
     */
    @ConcurrentParameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();

        // windows 7, Chrome 41
        browsers.add(["Windows 7", "41", "chrome", null, null] as String[]);

        // windows xp, IE 8
        browsers.add(["Windows XP", "8", "internet explorer", null, null] as String[]);

        // windows 7, IE 9
        browsers.add(["Windows 7", "9", "internet explorer", null, null] as String[]);
        /*
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



    @Test
    public void testUncheckedCheckBox(){
        this.testPage.checkUncheckedCheckBox();
        assertTrue("Unchecked check box is not checked!", this.testPage.getUncheckedCheckBoxState());
    }
    @Test
    public void testCheckedCheckBox(){
        this.testPage.uncheckCheckedCheckBox();
        assertFalse("Checked check box is checked!", this.testPage.getCheckedCheckBoxState());
    }
    @Test
    public void testCommentSubmit(){
        String testPhrase = 'Hello, world!';
        String testEmail = 'foo@bar.com';
        this.testPage.enterEmailText(testEmail);
        this.testPage.setCommentText(testPhrase);
        this.testPage.submitForm();
        String commentDisplayed = this.testPage.getSubmittedCommentText()
        assertTrue("Expected: " + testPhrase + " Got: " + commentDisplayed, commentDisplayed.endsWith(commentDisplayed));
    }

    @Test
    public void testEnterEmail(){
        String testEmail = 'foo@bar.com';
        this.testPage.enterEmailText(testEmail);
        String emailDisplayed = this.testPage.getEmailText();
        assertTrue("Expected: " + testEmail + " Got: " + emailDisplayed, emailDisplayed == testEmail);
    }

    @Test
    public void testUncheckedCheckBoxResetAfterSubmit(){
        this.testPage.checkUncheckedCheckBox();
        this.testPage.submitForm();
        assertFalse("Unchecked check box is checked!", this.testPage.getUncheckedCheckBoxState());
    }
    @Test
    public void testCheckedCheckBoxResetAfterSubmit(){
        this.testPage.uncheckCheckedCheckBox();
        this.testPage.submitForm();
        assertTrue("Checked check box is unchecked!", this.testPage.getCheckedCheckBoxState());
    }

}