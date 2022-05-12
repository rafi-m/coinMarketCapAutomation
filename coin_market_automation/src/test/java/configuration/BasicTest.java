package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import helpers.TestVariables;
//import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.core.api.Scenario;

@Configuration("classpath:cucumber.xml")
public class BasicTest {
	@Autowired
	public TestVariables variables;
	String date;
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	DateFormat formatTestEndTimestamp = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//	@Autowired
//	public ScenarioContext context;
	@Autowired
	public static Scenario scenario;

	public static void waitForElementToBeClickable(MobileElement element, String eleName,
			AppiumDriver<MobileElement> driver) throws Exception {
		System.out.println("Waiting for element to be clickable--> " + eleName);
		WebDriverWait wait = new WebDriverWait(driver,10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			System.out.println("Waiting for element to be clickable in catch Loop--> " + eleName);
		}
		System.out.println("Done Waiting for element to be clickable--> " + eleName);
	}

	public static void waitForElementToBeClickable(WebElement element, String eleName, WebDriver driver)
			throws Exception {
		System.out.println("Waiting for element to be clickable--> " + eleName);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			System.out.println("Waiting for element to be clickable in catch Loop--> " + eleName);
		}
		System.out.println("Done Waiting for element to be clickable--> " + eleName);
	}

	public static void waitForElementToBeVisible(WebElement element, String eleName, WebDriver driver)
			throws Exception {
		System.out.println("Waiting for element --> " + eleName);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			System.out.println("Wait for element Catch Loop--> " + eleName);
		}
		System.out.println("Done Waiting for element --> " + eleName);
	}

	public static void waitForElementToBeVisible(MobileElement element, String eleName,
			AppiumDriver<MobileElement> driver) throws Exception {
		System.out.println("Waiting for element --> " + eleName);
		WebDriverWait wait = new WebDriverWait(driver,10);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			System.out.println("Wait for element Catch Loop--> " + eleName);
		}
		System.out.println("Done Waiting for element --> " + eleName);
	}

	public static Boolean isElementPresent(MobileElement ele, String eleName, AppiumDriver<MobileElement> driver)
			throws Exception {
		Boolean match = false;
		try {
			match = ele.isDisplayed();
			System.out.println(eleName + " displayed ---> " + match);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		} catch (Exception e) {
			System.out.println(eleName + " displayed ---> " + match);
			System.out.println(e);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		}
	}

	public static Boolean isElementEnabled(MobileElement ele, String eleName, AppiumDriver<MobileElement> driver)
			throws Exception {
		Boolean match = false;
		try {
			match = ele.isEnabled();
			System.out.println(eleName + " displayed ---> " + match);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		} catch (Exception e) {
			System.out.println(eleName + " displayed ---> " + match);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		}
	}

	public static Boolean isElementPresent(WebElement ele, String eleName, WebDriver driver) throws Exception {
		Boolean match = false;
		try {
			match = ele.isDisplayed();
			System.out.println(eleName + " displayed ---> " + match);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		} catch (Exception e) {
			System.out.println(eleName + " displayed ---> " + match);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		}
	}

	public static Boolean isElementEnabled(WebElement ele, String eleName, WebDriver driver) throws Exception {
		Boolean match = false;
		try {
			match = ele.isEnabled();
			System.out.println(eleName + " displayed ---> " + match);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		} catch (Exception e) {
			System.out.println(eleName + " displayed ---> " + match);
			scenario.write(eleName + " displayed ---> " + match);
			return match;
		}
	}

	public void clickElementUsingAssessibilityId(String value, AppiumDriver<MobileElement> driver) throws Exception {
		MobileElement ele = driver.findElementByAccessibilityId(value);
		ele.click();
	}

	public String getExcelData(String sheetName, int rowNum, int colNum)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String filePath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		DataFormatter formatter = new DataFormatter();
		Cell cell = row.getCell(colNum);
		String data = formatter.formatCellValue(cell);
		return data;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException {
		String filePath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum() + 1;
		return rowCount;
	}

	@SuppressWarnings("deprecation")
	public void setExceldata(String sheetName, int rowNum, int colNum, String data)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String filePath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(colNum);
		cel.setCellType(Cell.CELL_TYPE_STRING);
		FileOutputStream fos = new FileOutputStream(filePath);
		cel.setCellValue(data);
		wb.write(fos);
		wb.close();

	}

	public String getExceldata(String sheetName, String rowValue, int colNum)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String data = null;
		int rowcount = getRowCount(sheetName);
		for (int i = 0; i < rowcount; i++) {
			if (rowValue.equals(getExcelData(sheetName, i, 0))) {
				data = getExcelData(sheetName, i, colNum);
				break;
			}
		}
		return data;
	}

	protected void scroll(Double fromX, Double fromY, Double toX, Double toY, AppiumDriver<MobileElement> driver) {
		Dimension dimensions = driver.manage().window().getSize();
		Double y = dimensions.getHeight() * fromY;
		int y1 = y.intValue();
		Double yy = dimensions.getHeight() * toY;
		int y2 = yy.intValue();
		Double x = dimensions.getWidth() * fromX;
		int x1 = x.intValue();
		Double xx = dimensions.getWidth() * toX;
		int x2 = xx.intValue();
		System.out.println("x,y --->" + dimensions);
		System.out.println("x1 --->" + x1);
		System.out.println("x2 --->" + x2);
		System.out.println("y1 --->" + y1);
		System.out.println("y2 --->" + y2);
		@SuppressWarnings("rawtypes")
		TouchAction touchAction = new TouchAction(driver);
		touchAction.longPress(PointOption.point(x1, y1)).moveTo(PointOption.point(x2, y2)).release().perform();
	}

	protected void clickOnCoordinateOfElement(MobileElement ele, AppiumDriver<MobileElement> driver) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction = new TouchAction(driver);
		Point location = ele.getLocation();
		System.out.println(location);
		int y = location.getY();
		int x = location.getX();
		touchAction.press(PointOption.point(x, y)).perform();
		System.out.println("Clicked on the co-ordinate --> " + location);
	}

	protected void clickOnAnyCoordinate(Double x, Double y, WebDriver driver) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
		Dimension dimensions = driver.manage().window().getSize();
		System.out.println(dimensions);
//		touchAction.press(PointOption.point(x1, y1)).perform();
		touchAction.tap(PointOption.point(x.intValue(), y.intValue())).perform();
		System.out.println("Clicked on the co-ordinate --> (" + x + ", " + y + ")");
	}

	protected void clickOnAnyCoordinateValue(int x, int y, AppiumDriver<MobileElement> driver) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(x, y)).perform();
		System.out.println("Clicked on the co-ordinate --> (" + x + ", " + y + ")");
	}

	protected void hideKeyboard(AppiumDriver<MobileElement> driver) {
		driver.hideKeyboard();
	}
	
	public static void scrollToTop(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)"); 
	}

	public void takeAndAttachScreenshot() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
		String formattedDate = sdf.format(date);
		String screenshotName = "Screenshot" + formattedDate;
		try {
			File sourcePath = ((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			byte[] screenshot = ((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			File destinationPath = new File(System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png");
			System.out.println(destinationPath);
			Files.copy(sourcePath, destinationPath);
			
			scenario.embed(screenshot, "image/png");
			ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destinationPath.getAbsolutePath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void takeAndAttachDashboardScreenshot() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
		String formattedDate = sdf.format(date);
		String screenshotName = "Screenshot" + formattedDate;
		try {
			byte[] screenshot = ((TakesScreenshot) LocalDriverManager.getDashboardDriver())
					.getScreenshotAs(OutputType.BYTES);
			File sourcePath = ((TakesScreenshot) LocalDriverManager.getDashboardDriver())
					.getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png");
			scenario.embed(screenshot, "image/png");
			Files.copy(sourcePath, destinationPath);
			
			ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destinationPath.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String takeAndAttachScreenshot1() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
		String formattedDate = sdf.format(date);
		String screenshotName = "Screenshot" + formattedDate;
		File destinationPath = null;
		try {
			File sourcePath = ((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			destinationPath = new File(System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png");
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
		}
		return destinationPath.toString();
	}

	public void selectDropdDownByValue(WebElement element, String value) {
		Select sel = new Select(element);
		List<WebElement> ls = sel.getOptions();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getText().toLowerCase().contains(value.toLowerCase())) {
				sel.selectByIndex(i);
			}
		}
	}

	public void acceptAlert(WebDriver driver) {

		System.out.println("Waiting for Alert --> ");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
		} catch (Exception e) {
			System.out.println("Wait for element Catch Loop--> " + e);
		}
		System.out.println("Done Waiting for element --> ");

	}

	public void waitForElementToBeInVisible(WebElement element, String eleName, WebDriver driver) throws Exception {
		System.out.println("Waiting for element --> " + eleName);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			System.out.println("Wait for element Catch Loop--> " + eleName);
		}
		System.out.println("Done Waiting for element --> " + eleName);
	}
}
