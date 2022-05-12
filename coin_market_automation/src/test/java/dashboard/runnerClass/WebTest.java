package dashboard.runnerClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/java/Dashboard/features" },
		glue = {"dashboard.stepDefinition"},
		tags = {"@web-task"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
		monochrome = true)
public class WebTest {
	@BeforeClass
	public static void setEnvironment() {
		System.setProperty("org.freemarker.loggerLibrary", "none");
	}
}  