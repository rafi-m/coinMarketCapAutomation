package dashboard.stepDefinition;

import configuration.BasicTest;
import configuration.DriverScript;
import configuration.LocalDriverManager;
import dashboard.pageObjects.ZoomAppHomePage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class ZoomAppStepDfn extends BasicTest{
    WebDriver driver = null;
    Scenario scenario;

    @Autowired
    ZoomAppHomePage zoomHome;

    @Before
    public void init(Scenario scenario) {
        BasicTest.scenario = scenario;
        this.scenario = scenario;
    }

    @Given("User opens zoom app on {string}")
    public void openZoomApp(String device) throws Exception {
        System.out.println("Opening Zoom App on given device");
        this.zoomHome.launchZoomApp(device);


    }
    @Given("Clicks join meeting")
    public void clickJoinMeeting() throws Exception {
        System.out.println("Clicking Join meeting");
        BasicTest.scenario.write("Clicking Join meeting");
        this.zoomHome.clickStartMeeting();

    }

    @Given("User gives meeting id {string}, toggle video off and joins the meeting")
    public void JoinsMeeting(String id) throws Exception {
        System.out.println("Joining the meeting..");
        BasicTest.scenario.write("Joining the meeting..");
        this.zoomHome.joinTheMeeting(id);
    }
    @Given("put the app in background for {int} seconds and launch it on foreground")
    public void minimizeTheApp(int seconds) throws Exception {
        this.zoomHome.minimize(seconds);
    }

    @Given("Error Message {string} should be visible")
    public void minimizeTheApp(String error) throws Exception {
        System.out.println("Validating Error Message..");
        BasicTest.scenario.write("Validating Error Message..");
        this.zoomHome.validateErrorMessage(error);
    }
}
