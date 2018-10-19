package me.edwardknight.pi_approximator;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SidePanel extends JPanel {
    private final JSpinner hitsSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    private final JSpinner missesSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    private final JLabel piResult = new JLabel("...");
    private final JLabel accuracyResult = new JLabel("...");

    public SidePanel() {
        super();
        // line on west side
        this.setBorder(new MatteBorder(0, 1, 0, 0, SystemColor.activeCaptionBorder));
        // Add components
        this.setLayout(makeLayout());
    }

    private GroupLayout makeLayout() {
        JLabel hitsLabel = new JLabel("Hits:");
        JLabel missesLabel = new JLabel("Misses:");
        JLabel piLabel = new JLabel("Pi:");
        JLabel accuracyLabel = new JLabel("Accuracy:");
        hitsSpinner.addChangeListener(e -> recalculatePi());
        missesSpinner.addChangeListener(e -> recalculatePi());

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(hitsLabel)
                    .addComponent(missesLabel)
                    .addComponent(piLabel)
                    .addComponent(accuracyLabel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(hitsSpinner)
                        .addComponent(missesSpinner)
                        .addComponent(piResult)
                        .addComponent(accuracyResult))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(hitsLabel)
                        .addComponent(hitsSpinner))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(missesLabel)
                        .addComponent(missesSpinner))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(piLabel)
                        .addComponent(piResult))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(accuracyLabel)
                        .addComponent(accuracyResult))
        );
        return layout;
    }

    public void incrementHits() {
        hitsSpinner.setValue(hitsSpinner.getNextValue());
        recalculatePi();
    }

    public void incrementMisses() {
        missesSpinner.setValue(missesSpinner.getNextValue());
        recalculatePi();
    }

    public void recalculatePi() {
        double hits = (int) hitsSpinner.getValue();
        double misses = (int) missesSpinner.getValue();
        if (hits > 0) {
            double pi = 4 * (hits / (hits + misses));
            piResult.setText(String.valueOf(pi));
            recalculateAccuracy();
        }
    }

    public void recalculateAccuracy() {
        double pi = Double.parseDouble(piResult.getText());
        double accuracy = 100 - ((Math.abs(pi - Math.PI) / Math.PI) * 100);
        accuracyResult.setText(String.valueOf(accuracy) + "%");
    }
}
