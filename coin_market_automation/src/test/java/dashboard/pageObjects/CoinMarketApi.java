package dashboard.pageObjects;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import configuration.BasicTest;
import helpers.CapabilitiesHelper;
import models.Models;

import org.assertj.core.api.SoftAssertions;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import helpers.RestClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CoinMarketApi {
    private String apiKey;
    private String host;
    CapabilitiesHelper capablities;

    public CoinMarketApi() throws IOException {
        this.capablities = new CapabilitiesHelper();
        this.host = capablities.getCapablities("coinMarket").get("host");
        this.apiKey = capablities.getCapablities("coinMarket").get("apiKey");;
    }

    public Models.MapResponse getAllCryptoCurrencyMap() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-CMC_PRO_API_KEY", this.apiKey);
        String response = RestClient.getRequest(this.host+"/v1/cryptocurrency/map", headers, null);
        // System.out.println(response);
        Models.MapResponse maps = new Gson().fromJson(response, Models.MapResponse.class);
        return maps;
    }

    public Double convertCurrency(String source, String amount, String dest) throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-CMC_PRO_API_KEY", this.apiKey);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", source);
        params.put("amount", amount);
        params.put("convert", dest);
        String response = RestClient.getRequest(this.host+"/v2/tools/price-conversion", headers, params);
        Double convertedValue = JsonPath.read(response, "$.data.quote.BOB.price");
        System.out.println(convertedValue);
        return convertedValue;
    }

    public HashMap<String, Models.Info> getTechnicalDocumentation(String id) throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-CMC_PRO_API_KEY", this.apiKey);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String response = RestClient.getRequest("https://pro-api.coinmarketcap.com/v1/cryptocurrency/info", headers, params);
        ObjectMapper obj = new ObjectMapper();
        Models.InfoResponse infoResponse = new Gson().fromJson(response, Models.InfoResponse.class);
        HashMap<String, Models.Info> currencies = new HashMap<>();
        return infoResponse.data;
    }

    public void assertCurrenyInformation(Map<String,String> assertions, HashMap<String, Models.Info> currencies,SoftAssertions soft){
        String id = assertions.get("id");
        if (assertions.containsKey("logo")) {
            soft.assertThat(currencies.get(id).logo).isEqualTo(assertions.get("logo"));
       //     Assert.assertEquals(currencies.get(id).logo, assertions.get("logo"));
            System.out.println(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Logo", assertions.get("logo"), currencies.get(id).logo));
            BasicTest.scenario.write(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Logo", assertions.get("logo"), currencies.get(id).logo));
        }

        if (assertions.containsKey("symbol")) {
           soft.assertThat(assertions.get("symbol")).isEqualTo(currencies.get(id).symbol);
        //    Assert.assertEquals(currencies.get(id).symbol, assertions.get("symbol"));
            System.out.println(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Symbol", assertions.get("symbol"), currencies.get(id).symbol));
            BasicTest.scenario.write(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Symbol", assertions.get("symbol"), currencies.get(id).symbol));
        }

        if (assertions.containsKey("date_added")) {
            soft.assertThat( assertions.get("date_added")).isEqualTo(currencies.get(id).date_added);
         //   Assert.assertEquals(currencies.get(id).date_added, assertions.get("date_added"));
            System.out.println(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Date Added", assertions.get("date_added"), currencies.get(id).date_added));
            BasicTest.scenario.write(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Date Added", assertions.get("date_added"), currencies.get(id).date_added));
        }

        if (assertions.containsKey("tech_doc")) {
            soft.assertThat( currencies.get(id).urls.get("technical_doc").size() > 0).isTrue();
       //     Assert.assertTrue(currencies.get(id).urls.get("technical_doc").size() > 0);
            System.out.println(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Technical Documentation", ">0", currencies.get(id).urls.get("technical_doc").size()));
            BasicTest.scenario.write(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Technical Documentation", ">0", currencies.get(id).urls.get("technical_doc").size()));
        }

        if (assertions.containsKey("tags")) {
         //   Assert.assertNotNull(currencies.get(id).tags);
            soft.assertThat(currencies.get(id).tags).isNotNull();
            if(currencies.get(id).tags!=null)
            soft.assertThat( currencies.get(id).tags.contains(assertions.get("tags"))).isTrue();
         //   Assert.assertTrue(currencies.get(id).tags.contains(assertions.get("tags")));
            System.out.println(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Tags", assertions.get("tags"), currencies.get(id).tags));
            BasicTest.scenario.write(String.format("Key: %s\r\nExpected:%s\r\nActual: %s", "Tags", assertions.get("tags"), currencies.get(id).tags));
        }

    }
}
