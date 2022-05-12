package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paulhammant.ngwebdriver.NgWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

@Component
public class DriverScript extends LocalDriverManager {

	public static Properties pConfigFile = new Properties();
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverScript.class);
	public static AppiumDriver<MobileElement> driver = null;
	public static WebDriver dashboardDriver = null;
	String strDriverName, strBrowserVersion, strBrowserName, strExecutionEnvironment;
	protected String meetingRoom;
	protected String strUserName, strPassword;
	public static String accessKey = "xzqsUvGX6sC62Xa1bZqU";
	public static String userName = "jcibrowerstack1";


//	public DriverScript() {
//		fnLoadAppConfigPropertyValuesFrom("config/ApplicationConfiguration.properties");
//		System.setProperty("logFileNameSuffix",
//				(new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss_a").format(new Date())).toString());
//		// PropertyConfigurator.configure(pConfigFile.getProperty("LOGPROPERTIES_FILEPATH"));
//	}

//	public void fnLoadAppConfigPropertyValuesFrom(final String strConfigFilePath) {
//		try {
//			pConfigFile.load(new FileInputStream(strConfigFilePath));
//			String strExecutionEnvironment = pConfigFile.getProperty("EXECUTION_ENVIRONMENT");
//			if (strExecutionEnvironment.equalsIgnoreCase("qa")) {
//				strUserName = pConfigFile.getProperty("USERNAME");
//				strPassword = pConfigFile.getProperty("PASSWORD");
//				pConfigFile.load(new FileInputStream("config/MeetingName.properties"));
//				meetingRoom = pConfigFile.getProperty("meetingRoom");
//			} else {
//				System.out.println(
//						"Environment name is not set.Please update the ENVIRONMENT_NAME in ApplicationConfiguration sheet");
//			}
//		} catch (IOException e) {
//			LOGGER.error("Unable to fetch properties!!!!");
//		}
//	}

//	public AppiumDriver<MobileElement> StartDriver(String device) throws Exception {
//		DesiredCapabilities capabilities = new DesiredCapabilities();
////		env = new EnvironmentHelper();
//		//device = device+"_"+this.env.env;
//		//HashMap<String, String> capMap = this.env.getDeviceCapablities(device);
//		for (String key : capMap.keySet()) {
//			capabilities.setCapability(key, capMap.get(key));
//		}
//		String url = null;
//		if (device.startsWith("bs")) {
//			url = "http://hub.browserstack.com/wd/hub";
//		} else {
//			url = "http://127.0.0.1:4723/wd/hub";
//		}
//		if (device.contains("ios")) {
//			driver = new IOSDriver<MobileElement>(new URL(url), capabilities);
//		} else {
//			driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);
//		}
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		return driver;
//	}





	public WebDriver startDashboardDriver() {
//		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		try {
			LocalDriverManager.getDashboardDriver().quit();
		} catch (Exception e) {
		}
		dashboardDriver = new ChromeDriver();
		dashboardDriver.manage().window().maximize();
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
		return dashboardDriver;
	}

}
