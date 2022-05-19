package configuration;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import helpers.CapabilitiesHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import com.paulhammant.ngwebdriver.NgWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@Component
public class DriverScript extends LocalDriverManager {

	public static AppiumDriver<MobileElement> mobileDriver = null;
	public static WebDriver chromeDriver = null;
	public HashMap<String,String> currentCapablities;

	public AppiumDriver<MobileElement> StartMobileDriver(String device) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		HashMap<String, String> capMap = new CapabilitiesHelper().getCapablities(device);
		this.currentCapablities = capMap;
		for (String key : capMap.keySet()) {
			capabilities.setCapability(key, capMap.get(key));
		}
		String url = "http://hub.browserstack.com/wd/hub";
		if (device.contains("ios")) {
			mobileDriver = new IOSDriver<MobileElement>(new URL(url), capabilities);
		} else {
			mobileDriver = new AndroidDriver<MobileElement>(new URL(url), capabilities);
		}
		mobileDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return mobileDriver;
	}

	public WebDriver startDashboardDriver() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		try {
			LocalDriverManager.getDashboardDriver().quit();
		} catch (Exception e) {
		}
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().window().maximize();
		JavascriptExecutor jsDriver = (JavascriptExecutor) mobileDriver;
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
		return chromeDriver;
	}

}
