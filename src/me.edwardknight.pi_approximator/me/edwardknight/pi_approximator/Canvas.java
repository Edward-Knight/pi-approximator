package me.edwardknight.pi_approximator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collection;

public class Canvas extends JPanel {
    private static final Color COLOR_BACKGROUND = Color.white;
    private static final Color COLOR_CIRCLE = Color.black;
    private static final Color COLOR_HIT = Color.green;
    private static final Color COLOR_MISS = Color.red;

    private final Ellipse2D circle = new Ellipse2D.Double();
    private final Collection<Point> points = new ArrayList<>();

    public Canvas() {
        super();
    }

    /**
     * Completely reset the drawing.
     */
    public void reset() {
        // reset circle
        Dimension size = this.getSize();
        if (size.width < size.height) {
            int pad = (size.height - size.width) / 2;
            circle.setFrame(0, pad, size.width, size.width);
        } else {
            int pad = (size.width - size.height) / 2;
            circle.setFrame(pad, 0, size.height, size.height);
        }

        // reset points
        points.clear();

        // redraw all
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // todo: redraw background
        g2.setColor(COLOR_BACKGROUND);

        // redraw circle
        g2.setColor(COLOR_CIRCLE);
        g2.draw(circle);

        // redraw all points
        for (Point point : points) {
            g2.setColor(circle.contains(point) ? COLOR_HIT : COLOR_MISS);
            g2.drawLine(point.x, point.y,
                        point.x, point.y);
        }
    }

    @Override
    public void update(Graphics g) {
        super.update(g);

        // todo: find some way of passing in new point information
    }
}
