package exemple;

import java.util.Scanner;

import javax.swing.*;

public class HashTable {
    private Node[] table;
    private LinkedListGUI gui;

    public HashTable(int size, LinkedListGUI gui) {
        this.table = new Node[size];
        this.gui = gui;
    }

    private int hash(String key) {
        int hash = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hash = (31 * key.charAt(i) + hash) % table.length ;
        }
        return hash;
    }

    public Integer add(String key) {
        int index = hash(key);
        Node newNode = new Node(key, index);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node temp = table[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Added: " + key);
        updateGUI();
        gui.startInsertAnimation(index);
        return index;
    }

    public boolean remove(String key) {
        int index = hash(key);
        Node head = table[index];

        if (head == null) {
            return false;
        }

        if (head.key.equals(key)) {
            table[index] = head.next;
            updateGUI();
            return true;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.key.equals(key)) {
                temp.next = temp.next.next;
                updateGUI();
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    public boolean contains(String key) {
        int index = hash(key);
        Node temp = table[index];

        while (temp != null) {
            if (temp.key.equals(key)) {
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    public int size() {
        int count = 0;
        for (Node head : table) {
            Node temp = head;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
        }
        return count;
    }

    public void display() {
        for (int i = 0; i < table.length; i++) {
            Node temp = table[i];
            System.out.println("Index " + i + ":");
            while (temp != null) {
                System.out.println("    Key: " + temp.key + ", Value: " + temp.value);
                temp = temp.next;
            }
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void task(String input) {
        String[] tokens = input.split(" ");
        String option = tokens[0];

        switch (option) {
            case "add":
                if (tokens.length >= 2) {
                    String key = tokens[1];
                    this.add(key);
                } else {
                    System.out.println("Key is required for add operation");
                }
                break;
            case "remove":
                if (tokens.length >= 2) {
                    String key = tokens[1];
                    boolean removed = this.remove(key);
                    if (!removed) {
                        System.out.println("Key not found");
                    }
                } else {
                    System.out.println("Key is required for remove operation");
                }
                break;
            case "size":
                System.out.println(this.size());
                break;
            case "contains":
                if (tokens.length >= 2) {
                    String key = tokens[1];
                    System.out.println(this.contains(key));
                } else {
                    System.out.println("Key is required for contains operation");
                }
                break;
            case "display":
                this.display();
                break;
            case "exit":
                this.exit();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void updateGUI() {
        SwingUtilities.invokeLater(() -> gui.updateData(table));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedListGUI gui = new LinkedListGUI();
        HashTable hashTable = new HashTable(10, gui);

        JFrame frame = new JFrame("HashTable Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gui);
        frame.setSize(800, 600);
        frame.setVisible(true);

        System.out.println("Enter choice:");

        while (true) {
            try {
                String choice = scanner.nextLine();
                hashTable.task(choice);
                if ("exit".equals(choice)) {
                    break;
                }
            } catch (Exception error) {
                System.out.println("Error: " + error.getMessage());
            }
        }

        scanner.close();
    }
}