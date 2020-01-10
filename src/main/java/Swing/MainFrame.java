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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void execute(){
        setVisible(true);
    }

    public void addCommand(String commandKey, Command command){
        commands.put(commandKey,command);
    }

    private void createUI(){
        setTitle("Money Calculator");
        moneyDialog=new SwingMoneyDialog(currencies);
        add( (Component) moneyDialog,BorderLayout.NORTH);
        moneyDisplay=new SwingMoneyDisplay();
        add((Component) moneyDisplay, BorderLayout.CENTER);
        add(calculateButton(),BorderLayout.SOUTH);
    }

    private JButton calculateButton(){
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get(button.getText()).execute());
        return button;
    }


    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }
}
