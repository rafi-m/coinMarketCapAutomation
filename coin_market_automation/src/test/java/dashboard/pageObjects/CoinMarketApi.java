package dashboard.pageObjects;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import models.Models;

import org.assertj.core.api.SoftAssertions;
import org.springframework.stereotype.Component;
import helpers.RestClient;

import java.util.HashMap;
import java.util.Map;

@Component
public class CoinMarketApi {
    private String apiKey;

    public CoinMarketApi(){
        this.apiKey = "44ad0aef-5de2-4947-9dc5-b0f49b89a3f9";
    }

    public Models.MapResponse getAllCryptoCurrencyMap() throws Exception {
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("X-CMC_PRO_API_KEY",this.apiKey);
       String response = RestClient.getRequest("https://pro-api.coinmarketcap.com/v1/cryptocurrency/map",headers,null);
     // System.out.println(response);
        Models.MapResponse maps = new Gson().fromJson(response, Models.MapResponse.class);
        return maps;
    }

    public Double convertCurrency(String source,String amount,String dest) throws Exception{
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("X-CMC_PRO_API_KEY",this.apiKey);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",source);
        params.put("amount",amount);
        params.put("convert",dest);
        String response = RestClient.getRequest("https://pro-api.coinmarketcap.com/v2/tools/price-conversion",headers,params);
        Double convertedValue = JsonPath.read(response,"$.data.quote.BOB.price");
        System.out.println(convertedValue);
        return  convertedValue;
    }

    public void getTechnicalDocumentation(String id) throws Exception {
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("X-CMC_PRO_API_KEY",this.apiKey);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        String response = RestClient.getRequest("https://pro-api.coinmarketcap.com/v1/exchange/info",headers,params);
        System.out.println(response);
        String logo = JsonPath.read(response,"$.data.1027.logo");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat("https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png").isEqualTo(logo);
        softAssertions.assertAll();

    }
}
