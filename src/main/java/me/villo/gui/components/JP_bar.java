package me.villo.gui.components;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JP_bar extends JPanel {

    private JTextField JTF_MsgBar;
    private JButton JB_send;

    public JP_bar() {

        setLayout(new GridLayout(2, 1));

        JTF_MsgBar = new JTextField();
        JB_send = new JButton("Invia");

        add(JTF_MsgBar);
        add(JB_send);
    }

}
