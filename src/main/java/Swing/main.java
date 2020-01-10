package Swing;

import Controller.CalculateCommand;
import View.Persistence.CurrencyListLoader;
import View.Persistence.ExchangeRateLoader;
import View.Persistence.RestCurrencyListLoader;
import View.Persistence.RestExchangeRateLoader;
import Swing.MainFrame;

public class main {

    public static void main(String[] args) {


        CurrencyListLoader currencyListLoader = new RestCurrencyListLoader();
        ExchangeRateLoader exchangeRateLoader = new RestExchangeRateLoader();
        MainFrame mainFrame = new MainFrame(currencyListLoader.currencies());
        mainFrame.add("calculate",new CalculateCommand(mainFrame.getMoneyDialog(),mainFrame.getMoneyDisplay(),
                exchangeRateLoader));

    }
}
