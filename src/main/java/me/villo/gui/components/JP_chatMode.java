package me.villo.gui.components;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JP_chatMode extends JPanel {

    private JRadioButton JRB_broadcast;
    private JRadioButton JRB_private;
    private ButtonGroup BG_chatMode;

    public JP_chatMode() {
        setLayout(new GridLayout(1,2));
        BG_chatMode = new ButtonGroup();

        JRB_broadcast = new JRadioButton("Broadcast");
        JRB_private = new JRadioButton("Privata");

        BG_chatMode.add(JRB_broadcast);
        BG_chatMode.add(JRB_private);

        add(JRB_broadcast);
        add(JRB_private);
    }

    public JRadioButton getJRB_broadcast() {
        return JRB_broadcast;
    }

    public JRadioButton getJRB_private() {
        return JRB_private;
    }

    public ButtonGroup getBG_chatMode() {
        return BG_chatMode;
    }

}
