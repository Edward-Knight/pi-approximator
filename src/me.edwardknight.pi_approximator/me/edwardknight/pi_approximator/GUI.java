package me.edwardknight.pi_approximator;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private final JFrame frame;

    /**
     * Initialises and shows GUI.
     */
    public GUI() {
        frame = makeFrame();
    }

    /**
     * Entry point, make and show GUI.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        GUI gui = new GUI();
    }

    /**
     * Make and show main GUI JFrame.
     */
    private JFrame makeFrame() {
        // Initialise frame
        JFrame frame = new JFrame("Pi Approximator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set minimum size
        frame.pack();
        frame.setMinimumSize(frame.getSize());

        // Set size to be quarter of screen size
        Dimension quarterSize = Toolkit.getDefaultToolkit().getScreenSize();
        quarterSize.setSize(quarterSize.getWidth() / 2, quarterSize.getHeight() / 2);
        frame.setSize(quarterSize);

        // Make visible and return
        frame.setVisible(true);
        return frame;
    }
}
