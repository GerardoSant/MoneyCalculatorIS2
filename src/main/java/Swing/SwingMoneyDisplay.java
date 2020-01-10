package Swing;

import Model.Money;
import View.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {

    JLabel moneyLabel;

    public void display(Money money) {
        moneyLabel.setText(money.getAmount() + " " + money.getCurrency().getCurrencySymbol() + " ("
        + money.getCurrency().getID() + ")");
    }

    public SwingMoneyDisplay() {
        createUI();
    }

    private void createUI() {
        moneyLabel=new JLabel("Money exchange");
        add(moneyLabel);
    }
}
