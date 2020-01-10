package View.Persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import Model.Currency;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;


public class RestCurrencyListLoader implements CurrencyListLoader {
    public List<Currency> currencies() {
        return getCurrenciesFromJson(getRestResponse());
    }


    private List<Currency> getCurrenciesFromJson(String JSON) {
        List<Currency> currencies = new ArrayList<Currency>();
        JsonParser parser= new JsonParser();
        JsonArray jArray = (JsonArray) parser.parse(fixJson(JSON)).getAsJsonObject().get("results");
        for (JsonElement elem : jArray){
            JsonObject obj = elem.getAsJsonObject();
            for (String currencyID : obj.keySet()){
                JsonObject jsonCurrencyObject = (JsonObject) obj.get(currencyID);
                currencies.add(getCurrencyFromJsonObject(jsonCurrencyObject));
            }
        }
        return currencies;

    }

    private Currency getCurrencyFromJsonObject(JsonObject jsonCurrencyObject) {
        String currencyName= jsonCurrencyObject.get("currencyName").toString().replaceAll("\"","");
        String currencyID=jsonCurrencyObject.get("id").toString().replaceAll("\"","");
        String currencySymbol;
        try{ currencySymbol=jsonCurrencyObject.get("currencySymbol").toString().replaceAll("\"",""); }
        catch(Exception e){ currencySymbol = "?";}
        return new Currency(currencyName,currencySymbol,currencyID);
    }

    private String fixJson(String JSON) {
        String fixedJson= JSON.substring(11, JSON.length()-1);
        fixedJson= "{\"results\":" + "[" + fixedJson + "]}";
        return fixedJson;
    }

    private String getRestResponse(){
        try {
            return Jsoup.connect("https://free.currconv.com/api/v7/currencies?apiKey=0cece5b713ab49acfc63").ignoreContentType(true).get().text();
        } catch(IOException exception){
            System.out.println("Failed to connect to Rest Currency API, trying again...");
            return getRestResponse();
        }
    }






}
