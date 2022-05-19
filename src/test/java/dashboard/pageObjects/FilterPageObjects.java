package dashboard.pageObjects;

import configuration.BasicTest;
import configuration.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class FilterPageObjects extends BasicTest {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//button[text()='Market Cap']")
    private WebElement btnMarketCap;
    @FindBy(xpath = "//button[text()='Price']")
    private WebElement btnPrice;
    @FindBy(xpath = "//button[text()='Show results']")
    private WebElement btnShowResults;
    @FindBy(xpath = "//button[text()='Apply Filter']")
    private WebElement btnApplyFilter;

    public void init() throws Exception {
        this.driver = LocalDriverManager.getDashboardDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 30);
    }
    public void setFilter(String filter, String value) throws Exception {
        switch (filter) {
            case "Market Cap Range":
                this.wait.until(ExpectedConditions.visibilityOf(this.btnMarketCap));
                this.btnMarketCap.click();
                String xpath = String.format("//button[text()='%s']",value);
                this.driver.findElement(By.xpath(xpath)).click();
                this.btnApplyFilter.click();
                break;
            case "Price":
                this.wait.until(ExpectedConditions.visibilityOf(this.btnPrice));
                this.btnPrice.click();
                xpath = String.format("//button[text()='%s']",value);
                this.driver.findElement(By.xpath(xpath)).click();
                this.btnApplyFilter.click();
        }

    }

    public void applyFilter(){
        this.btnShowResults.click();
    }
}
