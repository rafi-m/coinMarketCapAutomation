package dashboard.stepDefinition;

import java.util.List;
import java.util.Map;

import helpers.CapabilitiesHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import configuration.BasicTest;
import configuration.DriverScript;
import configuration.LocalDriverManager;


import dashboard.pageObjects.HomePageObjectsDashboard;
import io.cucumber.java.en.Then;

public class HomePageStepDefinitionDashboard extends BasicTest {

	WebDriver driver = null;
	@Autowired
	HomePageObjectsDashboard objHomePageObjects;
	@Autowired
	CapabilitiesHelper capabilities;
	Scenario scenario;
	@Before
	public void init(Scenario scenario) {
		BasicTest.scenario = scenario;
		this.scenario = scenario;
	}
	
   @Given("user navigates to {string}")
    public void homePageNavigation(String website) throws Exception {
	   String url = capabilities.getCapablities(website).get("url");
		DriverScript objDriverScript = new DriverScript();
		LocalDriverManager.setDashboardWebDriver(objDriverScript.startDashboardDriver());
		this.driver = LocalDriverManager.getDashboardDriver();
	   objHomePageObjects.init();
	   objHomePageObjects.navigateToHomePage(url);
	//	takeAndAttachDashboardScreenshot();
		}

	@Given("scrolls to the table")
	public void scrollToTable() throws Exception {
		objHomePageObjects.scrollToTable();
		this.scenario.write("Scrolling to table..");
		takeAndAttachDashboardScreenshot();
	}
	@Given("size of row is set to {string}")
	public void setSize(String size) throws Exception {
		objHomePageObjects.setSize(size);
		this.scenario.write("After Applying Row Size: " + size);
		takeAndAttachDashboardScreenshot();
	}

	@Then("verify if {int} rows are displayed")
	public void verify(int size) throws Exception {
	int	actualSize = objHomePageObjects.getTableSize(size);
	scenario.write(String.format("Expected Size: %d \t Actual Size: %d",size,actualSize));
	System.out.println(String.format("Expected Size: %d \t Actual Size: %d",size,actualSize));
	Assert.assertEquals(size,actualSize);
	}

	@When("below filters are applied")
	public void applyFilter(List<Map<String,String>> filters ) throws Exception {
		this.objHomePageObjects.NavigateToFilter(filters);
//		for(Map<String,String> filter:filters){
//			System.out.println(filter.get("filter"));
//		}
		takeAndAttachDashboardScreenshot();
	}

	@Then("the table should have rows with below values")
	public void verify(List<Map<String,String>> ranges) throws Exception {
		List<HomePageObjectsDashboard.Currency> actualValues = objHomePageObjects.getTableValues();
		BasicTest.scenario.write("*********************************************");
		for(HomePageObjectsDashboard.Currency curr:actualValues){
			objHomePageObjects.verifyTableResults(ranges,curr);

		}


	}

	}
