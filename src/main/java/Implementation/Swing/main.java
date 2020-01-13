package Implementation.Swing;

import Controller.CalculateCommand;
import View.Persistence.CurrencyListLoader;
import View.Persistence.ExchangeRateLoader;
import Implementation.RestPersistence.RestCurrencyListLoader;
import Implementation.RestPersistence.RestExchangeRateLoader;


public class main {

    public static void main(String[] args) {
        CurrencyListLoader currencyListLoader = new RestCurrencyListLoader();
        ExchangeRateLoader exchangeRateLoader = new RestExchangeRateLoader();
        MainFrame mainFrame = new MainFrame(currencyListLoader.currencies());
        mainFrame.addCommand("Calculate",new CalculateCommand(mainFrame.getMoneyDialog(),mainFrame.getMoneyDisplay(),
                exchangeRateLoader));
        mainFrame.execute();

    }
}
