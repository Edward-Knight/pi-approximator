package me.edwardknight.pi_approximator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Canvas extends JPanel {
    private final Ellipse2D circle = new Ellipse2D.Double();

    public Canvas() {
        super();
    }

    public void redrawCircle() {
        Dimension size = this.getSize();
        if (size.width < size.height) {
            int pad = (size.height - size.width) / 2;
            circle.setFrame(0, pad, size.width, size.width);
        } else {
            int pad = (size.width - size.height) / 2;
            circle.setFrame(pad, 0, size.height, size.height);
        }
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(circle);
    }
}
