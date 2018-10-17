package me.edwardknight.pi_approximator;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SidePanel extends JPanel {
    private final JTextField hitsField = new JTextField(4);
    private final JTextField missesField = new JTextField(4);

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
                        .addComponent(hitsField)
                        .addComponent(missesField))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(hitsLabel)
                        .addComponent(hitsField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(missesLabel)
                        .addComponent(missesField))
        );
        return layout;
    }

}
