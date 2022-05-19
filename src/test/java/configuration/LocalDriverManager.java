package configuration;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LocalDriverManager {
	private static ThreadLocal<AppiumDriver<MobileElement>> mobileDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public static AppiumDriver<MobileElement> getDriver() {
		return mobileDriver.get();
	}

	public static void setWebDriver(AppiumDriver<MobileElement> driver) {
		mobileDriver.set(driver);
	}

	public static void closeDriver(AppiumDriver<MobileElement> driver) {
		driver.close();
	}
	
	public static WebDriver getDashboardDriver() {
		return webDriver.get();
	}

	public static void setDashboardWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public static void closeDashboardDriver(WebDriver driver) {
		driver.close();
	}

}
