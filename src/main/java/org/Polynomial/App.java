package org.Polynomial;

import org.Polynomial.operations.Operations;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {
    private JTextField polynomial1Field, polynomial2Field;
    private JLabel resultLabel;

    public App() {
        setTitle("Polynomial Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        FlatDarkLaf.install();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(60, 63, 65));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel polynomial1Label = new JLabel("Polynomial 1:");
        polynomial1Label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        polynomial1Field = new JTextField(20);
        polynomial1Field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(polynomial1Label, gbc);
        gbc.gridx = 1;
        panel.add(polynomial1Field, gbc);


        JLabel polynomial2Label = new JLabel("Polynomial 2:");
        polynomial2Label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        polynomial2Field = new JTextField(20);
        polynomial2Field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(polynomial2Label, gbc);
        gbc.gridx = 1;
        panel.add(polynomial2Field, gbc);

        String[] operationTexts = {"+", "-", "*", "/", "Integrate", "Derive"};
        for (int i = 0; i < operationTexts.length; i++) {
            addButton(panel, operationTexts[i], i % 2, i / 2 + 2);
        }

        JLabel resultTitleLabel = new JLabel("Result:");
        resultTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        resultLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(resultTitleLabel, gbc);
        gbc.gridy = 8;
        panel.add(resultLabel, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void addButton(JPanel panel, String text, int gridX, int gridY) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(100, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(button, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {

                FlatDarkLaf.install();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            App calculator = new App();
            calculator.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Operations operations = new Operations();
        String polynomial1 = polynomial1Field.getText();
        String polynomial2 = polynomial2Field.getText();

        try {
            Polynomial p1;
            Polynomial p2;
            String operation = ((JButton) e.getSource()).getText();

            switch (operation) {
                case "+":
                    p1 = operations.parsePolynomial(polynomial1);
                    p2 = operations.parsePolynomial(polynomial2);
                    resultLabel.setText(operations.toString(operations.add(p1, p2)));
                    break;
                case "-":
                    p1 = operations.parsePolynomial(polynomial1);
                    p2 = operations.parsePolynomial(polynomial2);
                    resultLabel.setText(operations.toString(operations.subtract(p1, p2)));
                    break;
                case "*":
                    p1 = operations.parsePolynomial(polynomial1);
                    p2 = operations.parsePolynomial(polynomial2);
                    resultLabel.setText(operations.toString(operations.multiply(p1, p2)));
                    break;
                case "/":
                    p1 = operations.parsePolynomial(polynomial1);
                    p2 = operations.parsePolynomial(polynomial2);
                    DivisionResults divisionResults = operations.divide(p1, p2);
                    resultLabel.setText("<html>Quotient: " + operations.toString(divisionResults.getQuotient()) + "<br>Remainder: " + operations.toString(divisionResults.getRemainder()) + "</html>");
                    break;
                case "Integrate":
                    p1 = operations.parsePolynomial(polynomial1);
                    resultLabel.setText(operations.toString(operations.integrate(p1))+"+C");
                    break;
                case "Derive":
                    p1 = operations.parsePolynomial(polynomial1);
                    resultLabel.setText(operations.toString(operations.derive(p1)));
                    break;
            }
        } catch (IllegalArgumentException | IllegalStateException|ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



