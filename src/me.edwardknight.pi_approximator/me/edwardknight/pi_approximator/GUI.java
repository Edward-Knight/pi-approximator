package me.edwardknight.pi_approximator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GUI {
    private static final Image GITHUB_ICON;

    static {
        // Load GitHub icon
        Image icon = null;
        try {
            // NB: Could also use ClassLoader.getSystemResource("GitHub-Mark-32px.png")
            icon = ImageIO.read(GUI.class.getModule().getResourceAsStream("GitHub-Mark-32px.png"));
        } catch (IOException ignored) {}
        GITHUB_ICON = icon;
    }

    private final JFrame frame;
    private final Canvas canvas = new Canvas();
    private final SidePanel sidePanel;
    private final Timer timer = new Timer(10, e -> addRandomPoint());

    /**
     * Initialises and shows GUI.
     */
    public GUI() {
        sidePanel = new SidePanel(this);
        frame = makeFrame();
        canvas.reset();
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

        frame.setJMenuBar(makeMenuBar());

        // Add components
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(canvas, BorderLayout.CENTER);
        content.add(sidePanel, BorderLayout.EAST);

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

    /**
     * Create and fill a JMenuBar.
     */
    private JMenuBar makeMenuBar() {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        JMenuBar menuBar = new JMenuBar();
        JButton testButton = new JButton("Add point");
        testButton.addActionListener(e -> addRandomPoint());
        menuBar.add(testButton);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem gitHubItem = new JMenuItem("Go to GitHub", new ImageIcon(GITHUB_ICON));
        // todo: work out proper icon size
        ImageIcon gitHubMenuIcon = new ImageIcon(GITHUB_ICON.getScaledInstance(16, 16, Image.SCALE_SMOOTH), "GitHub logo");
        gitHubItem.setIcon(gitHubMenuIcon);
        gitHubItem.addActionListener(e -> browseToGitHub());
        helpMenu.add(gitHubItem);

        return menuBar;
    }

    public void addRandomPoint() {
        Point point = canvas.getRandomPoint();
        canvas.drawPoint(point);
        if (canvas.isHit(point)) {
            sidePanel.incrementHits();
        } else {
            sidePanel.incrementMisses();
        }
    }

    /**
     * Start or stop the timer repeatedly adding points.
     * @return false if the timer was stopped, true if the timer was started.
     */
    public boolean toggleAddingPoints() {
        if(timer.isRunning()) {
            timer.stop();
            return false;
        } else {
            timer.start();
            return true;
        }
    }

    /**
     * Helper method to browse to project on GitHub.
     */
    private void browseToGitHub() {
        try {
            Desktop.getDesktop().browse(new URI("https://gitHub.com/Edward-Knight/pi-approximator"));
        } catch (IOException | URISyntaxException ignored) {}
    }
}
