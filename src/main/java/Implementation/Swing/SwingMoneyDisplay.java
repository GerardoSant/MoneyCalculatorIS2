package Implementation.Swing;

import Model.Money;
import View.MoneyDisplay;

import javax.swing.*;
import java.text.DecimalFormat;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {

    JLabel moneyLabel;

    public void display(Money money) {
        DecimalFormat df = new DecimalFormat("#.###");
        moneyLabel.setText(df.format(money.getAmount()) + " " + money.getCurrency().getCurrencySymbol() + " ("
        + money.getCurrency().getID() + ")");
    }

    public SwingMoneyDisplay() {
        createUI();
    }

    private void createUI() {
        moneyLabel=new JLabel("Money exchange in EUR");
        add(moneyLabel);
    }
}
