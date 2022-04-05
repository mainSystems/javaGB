package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterApp extends JFrame {

    private int counter = 0;

    public CounterApp() {
        setTitle("CounterApp window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        //setBounds(500, 300, 315, 160);
        setSize(315, 160);
        setLocationRelativeTo(null);


        Font font = new Font("Arial", Font.BOLD, 30);

        JLabel counterView = new JLabel(String.valueOf(counter));
        counterView.setHorizontalAlignment(SwingConstants.CENTER);
        counterView.setFont(font);
        counterView.setBounds(100, 0, 150, 100);
        setLayout(null);
        add(counterView);

        JButton incrementButton = new JButton(">");
        incrementButton.setBounds(250, 0, 50, 100);
        add(incrementButton);
        JButton increment10Button = new JButton(">>");
        increment10Button.setBounds(200, 0, 50, 100);
        add(increment10Button);
        JButton decrementButton = new JButton("<");
        decrementButton.setBounds(0, 0, 50, 100);
        add(decrementButton);
        JButton decrement10Button = new JButton("<<");
        decrement10Button.setBounds(50, 0, 50, 100);
        add(decrement10Button);
        JButton clearButton = new JButton("clear");
        clearButton.setBounds(0, 100, 300, 20);
        add(clearButton);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == incrementButton) {
                    counter++;
                } else if (e.getSource() == decrementButton) {
                    counter--;
                } else if (e.getSource() == increment10Button) {
                    counter += 10;
                } else if (e.getSource() == decrement10Button) {
                    counter -= 10;
                } else if (e.getSource() == clearButton) {
                    counter = 0;
                }
                counterView.setText(String.valueOf(counter));
            }
        };

        decrementButton.addActionListener(actionListener);
        decrement10Button.addActionListener(actionListener);
        incrementButton.addActionListener(actionListener);
        increment10Button.addActionListener(actionListener);
        clearButton.addActionListener(actionListener);


        setVisible(true);
    }


    public static void main(String[] args) {
        new CounterApp();
    }
}