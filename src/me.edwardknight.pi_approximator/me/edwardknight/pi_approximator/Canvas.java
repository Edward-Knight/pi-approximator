package me.edwardknight.pi_approximator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Canvas extends JPanel {
    private static final Color COLOR_BACKGROUND = Color.white;
    private static final Color COLOR_CIRCLE = Color.black;
    private static final Color COLOR_HIT = Color.green;
    private static final Color COLOR_MISS = Color.red;

    private final Random random = new Random();
    /**
     * The square area of the canvas to draw in.
     * The area in which the background is drawn.
     */
    private final Rectangle backgroundSquare = new Rectangle();
    private final Ellipse2D circle = new Ellipse2D.Double();
    private final Collection<Point> points = new ArrayList<>();
    /**
     * Next point to draw, becomes null when point has been drawn.
     */
    private Point nextPoint = null;

    public Canvas() {
        super();
        addComponentListener(new ComponentAdapter() {
            /**
             * Completely resets the drawing.
             */
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                reset();
            }
        });
    }

    /**
     * Completely reset the drawing.
     * Automatically called whenever the component is resized.
     */
    public void reset() {
        // recalculate backgroundSquare
        Dimension size = getSize();
        if (size.width < size.height) {
            // pad height
            int pad = (size.height - size.width) / 2;
            backgroundSquare.setFrame(0, pad, size.width - 1, size.width - 1);
        } else {
            // pad width
            int pad = (size.width - size.height) / 2;
            backgroundSquare.setFrame(pad, 0, size.height - 1, size.height - 1);
        }

        // reset circle
        circle.setFrame(backgroundSquare);

        // reset points
        points.clear();

        // redraw all
        repaint();
    }

    public Point getRandomPoint() {
        return new Point(backgroundSquare.x + random.nextInt(backgroundSquare.width),
                         backgroundSquare.y + random.nextInt(backgroundSquare.height));
    }

    public boolean isHit(Point point) {
        return circle.contains(point);
    }

    /**
     * Schedule a new point to be drawn.
     * Will reject the new point if a point is already waiting to be drawn.
     *
     * @return False if already waiting for a point to be drawn.
     */
    public boolean drawPoint(Point point) {
        if (nextPoint != null) {
            // can't draw a new point yet, one still waiting to be drawn
            return false;
        }
        nextPoint = point;
        // todo: work out a way of just drawing the new point without redrawing everything
        repaint();
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.err.println("paintComponent");
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // redraw background
        g2.setColor(COLOR_BACKGROUND);
        g2.fill(backgroundSquare);

        // redraw circle
        g2.setColor(COLOR_CIRCLE);
        g2.draw(circle);

        // redraw all points
        if (nextPoint != null) {
            points.add(nextPoint);
        }
        for (Point point : points) {
            g2.setColor(isHit(point) ? COLOR_HIT : COLOR_MISS);
            // todo: decide on how large to make points
            g2.fillOval(point.x - 2, point.y - 2, 4, 4);
        }
        nextPoint = null;
    }

    @Override
    public void update(Graphics g) {
        System.err.println("update");
        super.update(g);
    }

    @Override
    public void repaint() {
        System.err.println("repaint");
        super.repaint();
    }
}
