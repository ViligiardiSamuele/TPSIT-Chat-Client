package me.villo.gui.components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JP_chats extends JPanel {

    private JTextArea JTA_broadcast;
    private JTextArea JTA_private;
    private JLabel JL_broadcast;
    private JLabel JL_private;

    public JP_chats() {
        setLayout(new GridLayout(4, 1));

        JL_broadcast = new JLabel("Broadcast chat");
        JL_private = new JLabel("Chat Privata");

        JTA_broadcast = new JTextArea();
        JTA_broadcast.setEditable(false);
        JTA_broadcast.setAutoscrolls(true);

        JTA_private = new JTextArea();
        JTA_private.setEditable(false);
        JTA_private.setAutoscrolls(true);

        add(JL_broadcast);
        add(new JScrollPane(JTA_broadcast));
        add(JL_private);
        add(new JScrollPane(JTA_private));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public JTextArea getJTA_broadcast() {
        return JTA_broadcast;
    }

    public JTextArea getJTA_private() {
        return JTA_private;
    }

    public JLabel getJL_broadcast() {
        return JL_broadcast;
    }

    public JLabel getJL_private() {
        return JL_private;
    }

}
