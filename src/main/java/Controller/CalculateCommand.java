package Controller;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import View.Persistence.ExchangeRateLoader;
import View.MoneyDialog;
import View.MoneyDisplay;

public class CalculateCommand implements Command {

    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    private ExchangeRateLoader exchangeRateLoader;


    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, ExchangeRateLoader exchangeRateLoader) {
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchangeRateLoader = exchangeRateLoader;
    }

    public void execute() {
        moneyDisplay.display(exchange(moneyDialog.get()));
    }

    private Money exchange(Money money) {
        Currency euroCurrency=new Currency("Euro","€","EUR");
        ExchangeRate exchangeRate = exchangeRateLoader.load(money.getCurrency(),euroCurrency);
        return new Money(money.getAmount()*exchangeRate.getRate(), euroCurrency);
    }
}
