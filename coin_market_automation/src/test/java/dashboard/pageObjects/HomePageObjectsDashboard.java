package dashboard.pageObjects;


import io.cucumber.core.api.Scenario;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import configuration.BasicTest;
import configuration.LocalDriverManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class HomePageObjectsDashboard extends BasicTest {
    WebDriver driver = null;
    @Autowired
    FilterPageObjects filterPage;

    WebDriverWait wait;
    @FindBy(xpath = "//div[@class='sc-16r8icm-0 tu1guj-0 cSTqvK']")
    private WebElement rows;

    @FindBy(xpath = "//div[@class='cmc-cookie-policy-banner__close']")
    private WebElement closeCookiesPermission;

    //@FindBy(xpath = "//button[text()='Filters']")
    @FindBy(xpath = "//body/div[@id='__next']/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[2]/div[3]/div[2]/button[1]")
    private WebElement filter;
    @FindBy(xpath = "//body/div[@id='__next']/div[1]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[5]/button[1]")
    private WebElement moreFilters;

    public HomePageObjectsDashboard() {
    }

    public void init() throws Exception {
        this.driver = LocalDriverManager.getDashboardDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 30);
    }

    public void navigateToHomePage(String url) throws Exception {
        this.driver.get(url);
        closeCookiesPermission.click();
    }

    public void scrollToTable() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", this.rows);

    }

    public void setSize(String sizeString) throws InterruptedException {
        this.rows.click();
        List<WebElement> sizes = this.driver.findElements(By.xpath("//div[@class='sc-16r8icm-0 sc-1f0grbq-0 cEoyCq']/button"));
        TimeUnit.SECONDS.sleep(1);
        for (WebElement size : sizes) {
            if (size.getText().equals(sizeString))
                size.click();
        }
        TimeUnit.SECONDS.sleep(3);
        this.scrollToTable();
    }

    public int getTableSize(int sizeString) throws InterruptedException {
        List<WebElement> sizes = this.driver.findElements(By.xpath("//table/tbody/tr"));
        return sizes.size();
    }

    public List<Currency> getTableValues() throws InterruptedException {
        List<WebElement> sizes = this.driver.findElements(By.xpath("//table/tbody/tr"));
        System.out.println("Current Filter Returns " + sizes.size() + " rows");
        BasicTest.scenario.write("Current Filter Returns " + sizes.size() + " rows");
        int i=1;
        List<Currency> currencies = new LinkedList<Currency>();
        for (WebElement row : sizes) {

            System.out.println("validating Row Number: " + i);
            BasicTest.scenario.write("validating Row Number: " + i++);
            	String name = row.findElement(By.xpath("./td[3]/div/a/div/div/p")).getText();
                String price = row.findElement(By.xpath("./td[4]/div[1]/a[1]/span[1]")).getText();
                String marketCap = row.findElement(By.xpath("./td[7]/p[1]/span[2]")).getText();
            System.out.println(String.format("Name: %s\tPrice: %s\tMarket Cap: %s",name,price,marketCap));
            BasicTest.scenario.write(String.format("Name: %s\tPrice: %s\tMarket Cap: %s",name,price,marketCap));
            Currency curr = new Currency(name,price,marketCap);
            currencies.add(curr);
        }
        return currencies;
    }

    public void verifyTableResults(List<Map<String,String>> expected,Currency currencies){
        BasicTest.scenario.write("Name: " + currencies.name);
        System.out.println("Name: " + currencies.name);
        for(Map<String,String> exp: expected){
            if(exp.get("filter").equals("Price")){
               float actualPrice = currencies.getPrice();
               float min = Float.parseFloat(exp.get("min"));
                float max = Float.parseFloat(exp.get("max"));
                Assert.assertTrue(min <= actualPrice && actualPrice <= max);
                BasicTest.scenario.write("Expected Price Range: " + min +"-"+max);
                System.out.println("Expected Price Range: $" + min +"-$"+max);
                BasicTest.scenario.write("Actual Price: " + currencies.price);
                System.out.println("Actual Price: $" + currencies.price);
            }
            else if(exp.get("filter").equals("Market Cap Range")){
                Long actualMarkCapRange = currencies.getMarketCap();
                Long min = Long.parseLong(exp.get("min"));
                Long max = Long.parseLong(exp.get("max"));
                Assert.assertTrue(min <= actualMarkCapRange && actualMarkCapRange <= max);
                BasicTest.scenario.write("Expected Market Cap Range: $" + min +"-$"+max);
                System.out.println("Expected Market Cap Range: $" + min +"-$"+max);
                BasicTest.scenario.write("Actual Market Cap Range: " + currencies.marketCap);
                System.out.println("Actual Market Cap Range: $" + currencies.marketCap);

            }
        }

        BasicTest.scenario.write("*********************************************");
    }



    public void NavigateToFilter(List<Map<String, String>> filters) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        this.filter.click();
        wait.until(ExpectedConditions.visibilityOf(this.moreFilters));
        this.moreFilters.click();
        this.filterPage.init();

        for (Map<String, String> filter : filters) {
            this.filterPage.setFilter(filter.get("filter"), filter.get("value"));
            TimeUnit.SECONDS.sleep(2);
        }
        takeAndAttachDashboardScreenshot();
        this.filterPage.applyFilter();
    }

    public  class Currency{
        public String name;
        public float price;
        public long marketCap;
        public Currency(String name,String price, String marketCap){
            this.name = name;
            String p = price.substring(1).replaceAll(",","");
            this.price = Float.parseFloat(p);
            String m = marketCap.substring(1).replaceAll(",","");
            this.marketCap = Long.parseLong(m);
        }

        public float getPrice(){
            return this.price;
        }
        public long getMarketCap(){
            return this.marketCap;
        }
    }

}



