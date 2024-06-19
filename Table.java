package exemple;

import javax.swing.*;
import java.awt.*;

public class Table extends JFrame {
    private HashTable ht;
    private LinkedListGUI gui;

    public Table() {
        gui = new LinkedListGUI();
        ht = new HashTable(10, gui); // Passing the GUI to the HashTable
        setTitle("HashTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center of the screen
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField textField = new JTextField(15);
        textField.setText("Input");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(textField, gbc);

        JTextField textField1 = new JTextField(15);
        textField1.setText("Output");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(textField1, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JButton addButton = new JButton("Add");
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(addButton, gbc);
        addButton.addActionListener(e -> {
            String input = textField.getText().trim();
            if (!input.isEmpty()) {
                ht.add(input);
                textField.setText("");
                textField1.setText("Added");
            }
        });

        JButton removeButton = new JButton("Remove");
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(removeButton, gbc);
        removeButton.addActionListener(e -> {
            String input = textField.getText().trim();
            if (!input.isEmpty()) {
                ht.remove(input);
                textField.setText("");
                textField1.setText("Removed");
            }
        });

        JButton displayButton = new JButton("Display");
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonPanel.add(displayButton, gbc);
        displayButton.addActionListener((e) -> {
            ht.display();
            textField1.setText("Displayed");
        });

        JButton sizeButton = new JButton("Size");
        gbc.gridx = 3;
        gbc.gridy = 1;
        buttonPanel.add(sizeButton, gbc);
        sizeButton.addActionListener((e) -> {
            int size = ht.size();
            textField1.setText("Size: " + size);
        });

        JButton exitButton = new JButton("Exit");
        gbc.gridx = 4;
        gbc.gridy = 1;
        buttonPanel.add(exitButton, gbc);
        exitButton.addActionListener((e) -> {
            ht.exit();
            textField1.setText("Exit");
        });

        // Adding panels to the frame
        getContentPane().add(buttonPanel, BorderLayout.EAST);
        getContentPane().add(inputPanel, BorderLayout.WEST);
        getContentPane().add(gui, BorderLayout.CENTER);

        pack();
    }

    public static void main(String[] args) {
        new Table().setVisible(true);
    }
}