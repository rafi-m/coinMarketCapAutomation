package dashboard.stepDefinition;

import configuration.BasicTest;
import dashboard.pageObjects.CoinMarketApi;
import helpers.CapabilitiesHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Models;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinMarketApiStepDefn extends BasicTest {
    Scenario scenario;
    String currencyId;
    String currencySymbol;
    @Autowired
    CoinMarketApi coinMarketApi;
    HashMap<String, Models.Info> currencies;

    @Before
    public void init(Scenario scenario) {
        BasicTest.scenario = scenario;
        this.scenario = scenario;
    }

    @Given("get the information about {string}")
    public void get_doc(String id) throws Exception {
        if(this.currencyId == null)
        this.currencies = this.coinMarketApi.getTechnicalDocumentation(id);
    }

    @Given("Fetch the id for {string}")
    public void fetch_the_id_below_currency(String currency) throws Exception {
        this.currencySymbol = currency;
        Models.MapResponse maps = coinMarketApi.getAllCryptoCurrencyMap();
        for (Models.Maps m : maps.data) {
            if (m.symbol.equals(currency))
                this.currencyId = m.id;
        }
        BasicTest.scenario.write(String.format("%s:%s", currency, this.currencyId));
    }

    @When("convert amount {string} to {string}")
    public void convert_amount_to(String amount, String conversionCurrency) throws Exception {
        Double converted = coinMarketApi.convertCurrency(this.currencyId, amount, conversionCurrency);
        System.out.println(String.format("Symbol: %s\r\nId: %s\r\nAmount: %s\r\nConversion Amount: %f", this.currencySymbol, this.currencyId, amount, converted));
        BasicTest.scenario.write(String.format("%s %s equals %f %s", amount, this.currencySymbol, converted, conversionCurrency));

    }

    @Then("verify following details")
    public void verifyCurrencyDetails(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        SoftAssertions soft = new SoftAssertions();
        for (Map<String, String> assertions : rows) {
            String id = assertions.get("id");
            Assert.assertTrue(currencies.containsKey(id));

            this.coinMarketApi.assertCurrenyInformation(assertions, this.currencies,soft);
        }
        soft.assertAll();

    }



}
