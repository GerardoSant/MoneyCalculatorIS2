package Implementation.RestPersistence;

import Model.Currency;
import Model.ExchangeRate;
import View.Persistence.ExchangeRateLoader;
import org.jsoup.Jsoup;

import java.io.IOException;

import static java.lang.Double.parseDouble;

public class RestExchangeRateLoader implements ExchangeRateLoader {
    public ExchangeRate load(Currency from, Currency to) {
        return new ExchangeRate(getExchangeRate(from, to), from, to);
    }


    private double getExchangeRate(Currency from, Currency to) {
        return getExchangeRateFromResponseString(getRestResponse(from.getID(),to.getID()));
    }

    private double getExchangeRateFromResponseString(String restResponse) {
        return parseDouble(restResponse.substring(restResponse.indexOf(":")+1,restResponse.indexOf("}",2)-1));
    }

    private String getRestResponse(String fromID, String toID) {
        try {
            return Jsoup.connect(getRestApiLink(fromID, toID)).ignoreContentType(true).get().text();
        } catch (IOException exception) {
            System.out.println("Failed to connect to Rest Currency API, trying again...");
            return getRestResponse(fromID, toID);
        }
    }

    private String getRestApiLink(String fromID, String toID) {
        return "https://free.currconv.com/api/v7/convert?apiKey=304cc95779048564da5f&q=" + fromID + "_"
                + toID + "&compact=ultra";
    }
}
