package me.edwardknight.pi_approximator;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SidePanel extends JPanel {
    private final JSpinner hitsSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    private final JSpinner missesSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

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

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(hitsLabel)
                    .addComponent(missesLabel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(hitsSpinner)
                        .addComponent(missesSpinner))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(hitsLabel)
                        .addComponent(hitsSpinner))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(missesLabel)
                        .addComponent(missesSpinner))
        );
        return layout;
    }

    public void incrementHits() {
        hitsSpinner.setValue(hitsSpinner.getNextValue());
    }

    public void incrementMisses() {
        missesSpinner.setValue(missesSpinner.getNextValue());
    }
}
