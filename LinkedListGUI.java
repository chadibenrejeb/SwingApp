package exemple;

import javax.swing.*;
import java.awt.*;

public class LinkedListGUI extends JPanel {
    private Node[] table;

    public LinkedListGUI() {
        this.table = new Node[10];
    }

    public void updateData(Node[] table) {
        System.out.println("updateData called");
        for (int i = 0; i < table.length; i++) {
            Node temp = table[i];
            System.out.print("Index " + i + ": ");
            while (temp != null) {
                System.out.print(temp.key + " -> ");
                temp = temp.next;
            }
            System.out.println("null");
        }
        this.table = table;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHashTable(g, table);
    }

    private void drawHashTable(Graphics g, Node[] table) {
        int x = 20;
        int y = 20;
        int width = 100;
        int height = 30;
        int padding = 10;

        for (int i = 0; i < table.length; i++) {
            g.setColor(Color.BLACK);
            g.drawRect(x, y + i * (height + padding), width, height);
            g.drawString("HashCode :"+ i, x + 10, y + i * (height + padding) + 20);

            Node current = table[i];
            int innerX = x + width + padding;

            while (current != null) {
                g.setColor(Color.BLUE);
                g.drawRect(innerX, y + i * (height + padding), width, height);
                g.drawString("Key: " + current.key, innerX + 10, y + i * (height + padding) + 20);
                //g.drawString("Value: " + current.value, innerX + 10, y + i * (height + padding) + 40);
                innerX += width + padding;
                current = current.next;
            }
        }
    }
}
