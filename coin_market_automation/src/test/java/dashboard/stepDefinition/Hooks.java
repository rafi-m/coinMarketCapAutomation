
package dashboard.stepDefinition;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;

import configuration.LocalDriverManager;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
//import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

public class Hooks {
	Scenario scenario;
	@Before
	public void beforeScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	@After("@ui")
	public void afterScenario(Scenario scenario) throws Exception {
		if (scenario.isFailed() && LocalDriverManager.getDashboardDriver() != null) {
			try {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
				String formattedDate = sdf.format(date);
				String screenshotName = "Screenshot" + formattedDate;
				File sourcePath = ((TakesScreenshot) LocalDriverManager.getDashboardDriver())
						.getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(
						System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png");
				final byte[] screenshot = ((TakesScreenshot) LocalDriverManager.getDashboardDriver())
						.getScreenshotAs(OutputType.BYTES);
				Files.copy(sourcePath, destinationPath);
				scenario.embed(screenshot, "image/png");
				ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destinationPath.getAbsolutePath());
			} catch (Exception e) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
				String formattedDate = sdf.format(date);
				String screenshotName = "Screenshot" + formattedDate;
				File sourcePath = ((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(
						System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png");
				final byte[] screenshot = ((TakesScreenshot) LocalDriverManager.getDriver())
						.getScreenshotAs(OutputType.BYTES);
				Files.copy(sourcePath, destinationPath);
				scenario.embed(screenshot, "image/png");
				ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destinationPath.getAbsolutePath());
			}
		}
	}

	@After(order = 0)
	public void AfterSteps() {
		try {
			if (LocalDriverManager.getDashboardDriver() != null)
				LocalDriverManager.getDashboardDriver().quit();
			
			if (LocalDriverManager.getDriver() != null)
				LocalDriverManager.getDriver().quit();
			
			
		} catch (Exception e) {
			LocalDriverManager.getDriver().quit();
		}
		
	}
	

}