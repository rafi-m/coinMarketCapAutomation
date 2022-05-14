package dashboard.runnerClass;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/java/Dashboard/features" },
		glue = {"dashboard.stepDefinition"},
	//	tags = {"@web-task2"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
		monochrome = true)
public class CoinMarketTest {
	@BeforeClass
	public static void setEnvironment() throws IOException {
		File f = new File("Screenshots");
		if(!f.exists()){
			System.out.println("Creating screenshot Directory");
			f.mkdir();
		}else{
			//deleting old screenshots to free up space
			FileUtils.cleanDirectory(new File("Screenshots"));
		}
		System.setProperty("org.freemarker.loggerLibrary", "none");
	}

	//disable if running in CI/CD
	@AfterClass
	public static void openHtmlReport() throws IOException {
		File htmlFile = new File("test-output/spark/index.html");
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

}  