package Swing;

import Controller.Command;
import Model.Currency;
import View.MoneyDialog;
import View.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private List<Currency> currencies;
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    private Map<String, Command> commands;

    public MainFrame(List<Currency> currencies) throws HeadlessException {
        this.currencies = currencies;
        commands = new HashMap<String, Command>();
        createUI();
    }

    public void add(String commandKey, Command command){
        commands.put(commandKey,command);
    }

    private void createUI(){
        setTitle("MoneyCalculator");
        moneyDialog=new SwingMoneyDialog(currencies);
        add( (Component) moneyDialog,BorderLayout.NORTH);
        moneyDisplay=new SwingMoneyDisplay();
        add((Component) moneyDisplay, BorderLayout.CENTER);
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(moneyDialog.get().getAmount() + " " + moneyDialog.get().getCurrency().toString());
                commands.get("calculate").execute();
            }
        });
        add(calculateButton,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }
}
