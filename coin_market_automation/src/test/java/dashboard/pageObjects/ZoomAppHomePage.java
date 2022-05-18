package dashboard.pageObjects;

import configuration.BasicTest;
import configuration.DriverScript;
import configuration.LocalDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class ZoomAppHomePage extends BasicTest {
    @Autowired
    DriverScript objDriverScript;
    AppiumDriver<MobileElement> driver = null;
    @FindBy(id = "us.zoom.videomeetings:id/btnJoinConf")
    private WebElement btnJoinMeeting;

    @FindBy(id = "us.zoom.videomeetings:id/edtConfNumber")
    private WebElement lblMeetingId;

    @FindBy(id = "us.zoom.videomeetings:id/chkNoVideo")
    private WebElement btnToggleVideo;
    @FindBy(id = "us.zoom.videomeetings:id/btnJoin")
    private WebElement btnJoin;
    @FindBy(id = "us.zoom.videomeetings:id/txtMsg")
    private WebElement lblErrorMsg;
    @FindBy(id = "us.zoom.videomeetings:id/button2")
    private WebElement btnOk;

    private WebDriverWait wait;

    public void launchZoomApp(String device) throws Exception {
        LocalDriverManager.setWebDriver(objDriverScript.StartMobileDriver(device));
        this.driver = LocalDriverManager.getDriver();
        PageFactory.initElements(this.driver, this);
        takeAndAttachScreenshot();
        this.wait = new WebDriverWait(driver, 30);
    }

    public void clickStartMeeting() throws Exception{
            this.btnJoinMeeting.click();
            wait.until(ExpectedConditions.visibilityOf(this.lblMeetingId));
            takeAndAttachScreenshot();
        Assert.assertFalse(this.btnJoin.isEnabled());
        System.out.println("Join Button is Disabled");
    }

    public void joinTheMeeting(String id) throws Exception{
        this.lblMeetingId.sendKeys(id);
        this.btnToggleVideo.click();
        takeAndAttachScreenshot();
        Assert.assertTrue(this.btnJoin.isEnabled());
        System.out.println("Join Button is Enabled");
        this.btnJoin.click();
        wait.until(ExpectedConditions.visibilityOf(this.lblErrorMsg));

    }

    public void minimize(long seconds) throws Exception{
        System.out.println("Putting the App on background");
        BasicTest.scenario.write("Putting the App on background");
        this.driver.runAppInBackground(Duration.ofSeconds(-1));
        takeAndAttachScreenshot();
        TimeUnit.SECONDS.sleep(seconds);
        System.out.println("Putting the App on foreground");
        BasicTest.scenario.write("Putting the App on foreground");
        String packageId = this.objDriverScript.currentCapablities.get("package-id");
        this.driver.activateApp(packageId);
        wait.until(ExpectedConditions.visibilityOf(this.lblErrorMsg));
        takeAndAttachScreenshot();
    }

    public void validateErrorMessage(String error) {
        String actualError = this.lblErrorMsg.getText();
        Assert.assertEquals(actualError,error);

    }
}
