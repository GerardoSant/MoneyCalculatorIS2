package Swing;

import Model.Currency;
import Model.Money;
import View.MoneyDialog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private JTextField moneyInput;
    private List<Currency> currencyList;
    private JComboBox currencyIDList;

    public SwingMoneyDialog(List<Currency> currencies) {
        currencyList=currencies;
        createUI();
    }

    public Money get() {
        return new Money(parseDouble(moneyInput.getText()), getSelectedCurrency());
    }

    private Currency getSelectedCurrency() {
        String currencyID= currencyIDList.getSelectedItem().toString();
        for (Currency currency : currencyList){
            if (currency.getID().equals(currencyID)){
                return new Currency(currency.getCurrencyName(),currency.getCurrencySymbol(), currency.getID());
            }
        }
        return null;
    }

    private void createUI(){
        moneyInput = new JTextField(20);
        currencyIDList = new JComboBox(getCurrencyIDList().toArray());
        add(moneyInput);
        add(currencyIDList);
    }

    public ArrayList<String> getCurrencyIDList(){
        ArrayList<String> currencyIDList = new ArrayList();
        for(Currency currency : currencyList){
            currencyIDList.add(currency.getID());
        }
        return currencyIDList;
    }

}
