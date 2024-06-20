package exemple;

import javax.swing.*;
import java.awt.*;

public class LinkedListGUI extends JPanel {
    private Node[] table;
    private int animationIndex = -1; // no animation 
    private int animationProgress = 0; // track the progress of the animation

    public LinkedListGUI() {
        this.table = new Node[10];
    }

    public void updateData(Node[] table) {
        this.table = table;
        repaint();
    }

    public void startInsertAnimation(int index) {
        animationIndex = index;
        animationProgress = 0;
        Timer timer = new Timer(100, e -> {
            animationProgress++;
            if (animationProgress >= 10) {
                ((Timer) e.getSource()).stop();
                animationIndex = -1;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawHashTable(g2d, table);
    }

    private void drawHashTable(Graphics g, Node[] table) {
        int x = 20;
        int y = 20;
        int width = 100;
        int height = 30;
        int padding = 10;
        int arrowSize = 10;

        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            int innerX = x + width + padding;

            // Draw hash code box
            g.setColor(Color.BLACK);
            g.drawRect(x, y + i * (height + padding), width, height);
            g.drawString("HashCode: " + i, x + 10, y + i * (height + padding) + 20);
        

            // Draw animated node moving into the linked list
            if (current != null && i == animationIndex ) {
                int animationX = x + width + padding + animationProgress *10;
                g.setColor(Color.BLUE);
                g.drawRect(animationX, y + i * (height + padding), width, height);
                g.drawString("Key: " + current.key, animationX + 10, y + i * (height + padding) + 20);
            }
            

            if (current != null && i != animationIndex) {
                // draw arrow from hash code box to the first node
                int x1 = x + width; // end of hash code box
                int y1 = y + i * (height + padding) + height / 2; // Center of hash code box
                int x2 = 243; // start of arrow line (start of first node)
                int y2 = y1; // center of hash code box

                // Draw arrow line
                g.setColor(Color.RED);
                g.drawLine(x1, y1, x2, y2);

                // Draw arrowhead // to create arrowhead in the shape of V 
                double angle = Math.atan2(y2 - y1, x2 - x1);
                int dx = (int) (arrowSize * Math.cos(angle - Math.PI / 6));
                int dy = (int) (arrowSize * Math.sin(angle - Math.PI / 6)); 
                g.drawLine(x2, y2, x2 - dx, y2 - dy);

                dx = (int) (arrowSize * Math.cos(angle + Math.PI / 6));
                dy = (int) (arrowSize * Math.sin(angle + Math.PI / 6));
                g.drawLine(x2, y2, x2 - dx, y2 - dy);

                // Move innerX to start drawing nodes
                innerX += width + padding;
            }

            while (current != null ) {
                if( i != animationIndex ){
                // Draw node rectangle
                g.setColor(Color.BLUE);
                g.drawRect(innerX , y + i * (height + padding), width, height);
                g.drawString("Key: " + current.key, innerX + 10, y + i * (height + padding) + 20);
                
                // Draw three parallel segments if it's the last node in the chain
                if (current.next == null) {
                    g.setColor(Color.RED);
                    int segmentLength = 20;
                    int segmentSpacing = 13;
                    g.drawLine(innerX + width + segmentLength, y + i * (height + padding) + 13, innerX + width + segmentLength, y + i * (height + padding));
                    g.drawLine(innerX + width + segmentLength, y + i * (height + padding) + 13, innerX + width + segmentLength, y + i * (height + padding) + 26);
                    g.drawLine(innerX + width, y + i * (height + padding) + 13, innerX + width + segmentLength, y + i * (height + padding) + 13);
                    for (int j = 0; j < 3; j++) {
                        int segmentY = y + i * (height + padding) + j * segmentSpacing;
                        g.drawLine(innerX + width + 20, segmentY, innerX + width + segmentLength + 20, segmentY - 10);
                    }
                }
            
            

                // Draw arrows to next node
                if (current.next != null) {
                    int x1 = innerX + width; // Start of arrow line
                    int y1 = y + i * (height + padding) + height / 2;
                    int x2 = innerX + width + padding; // End of arrow line
                    int y2 = y1;

                    // Draw arrow line
                    g.setColor(Color.RED);
                    g.drawLine(x1, y1, x2, y2);

                    // Draw arrowhead
                    double angle = Math.atan2(y2 - y1, x2 - x1);
                    int dx = (int) (arrowSize * Math.cos(angle - Math.PI / 6));
                    int dy = (int) (arrowSize * Math.sin(angle - Math.PI / 6));
                    g.drawLine(x2, y2, x2 - dx, y2 - dy);

                    dx = (int) (arrowSize * Math.cos(angle + Math.PI / 6));
                    dy = (int) (arrowSize * Math.sin(angle + Math.PI / 6));
                    g.drawLine(x2, y2, x2 - dx, y2 - dy);
                }
            }

                innerX += width + padding;
                current = current.next;
            }
        }
    }   
}