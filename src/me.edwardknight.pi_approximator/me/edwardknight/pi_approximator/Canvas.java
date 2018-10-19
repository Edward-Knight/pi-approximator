package me.edwardknight.pi_approximator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Canvas extends JPanel {
    private static final Color COLOR_BACKGROUND = Color.white;
    private static final Color COLOR_CIRCLE = Color.black;
    private static final Color COLOR_HIT = Color.green;
    private static final Color COLOR_MISS = Color.red;

    private final Random random = new Random();
    /**
     * The image that is drawn onto the canvas.
     */
    private BufferedImage image;
    /**
     * The offset from which the image should be drawn.
     */
    private int xOffset;
    private int yOffset;
    private final Ellipse2D circle = new Ellipse2D.Double();

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
        // recalculate xOffset and yOffset and make new image
        Dimension size = getSize();
        if (size.width < size.height) {
            // pad height
            xOffset = 0;
            yOffset = (size.height - size.width) / 2;
            image = new BufferedImage(size.width, size.width,
                                      BufferedImage.TYPE_INT_ARGB);
        } else {
            // pad width
            xOffset = (size.width - size.height) / 2;
            yOffset = 0;
            image = new BufferedImage(size.height, size.height,
                                      BufferedImage.TYPE_INT_ARGB);
        }

        // reset circle
        circle.setFrame(0, 0, image.getWidth(), image.getHeight());

        // redraw image
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // redraw background
        g2.setColor(COLOR_BACKGROUND);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());

        // redraw circle
        g2.setColor(COLOR_CIRCLE);
        // we draw the circle slightly smaller so the right and bottom edge show
        g2.draw(new Ellipse2D.Double(0, 0, circle.getWidth() - 1,
                                     circle.getHeight() - 1));

        g2.dispose();
        repaint();
    }

    public Point getRandomPoint() {
        return new Point(random.nextInt(image.getWidth()),
                         random.nextInt(image.getHeight()));
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
    public void drawPoint(Point point) {
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(isHit(point) ? COLOR_HIT : COLOR_MISS);
        // todo: add option to change point size
        g2.fillOval(point.x - 2, point.y - 2, 4, 4);
        g2.dispose();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, xOffset, yOffset, this);
    }
}
