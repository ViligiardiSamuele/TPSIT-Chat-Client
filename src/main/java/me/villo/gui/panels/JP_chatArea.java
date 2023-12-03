package me.villo.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import me.villo.ProtocolCodes;
import me.villo.gui.Main;

public class JP_chatArea extends JPanel {

    private JTextArea JTA_broadcast;
    private JTextArea JTA_private;
    private JLabel JL_broadcast;
    private JLabel JL_private;
    private JTextField JTF_MsgBar;

    public JP_chatArea() {
        setLayout(new BorderLayout(5, 5));
        /*
         * - Center Panel
         * -- Broadcast Panel
         * -- Private Panel
         * - Bottom Panel
         */

        // - CENTER PANEL
        JPanel JP_center = new JPanel(new GridLayout(2, 1, 5, 5));

        // -- BROADCAST PANEL
        JPanel JP_broadcast = new JPanel(new BorderLayout(5, 5));
        JL_broadcast = new JLabel("Broadcast chat");
        JTA_broadcast = new JTextArea();

        DefaultCaret caret_JTA_broadcast = (DefaultCaret) JTA_broadcast.getCaret();
        caret_JTA_broadcast.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JTA_broadcast.setEditable(false);
        JTA_broadcast.setAutoscrolls(true);
        JP_broadcast.add(JL_broadcast, BorderLayout.PAGE_START);
        JP_broadcast.add(new JScrollPane(JTA_broadcast), BorderLayout.CENTER);
        // -- BROADCAST PANEL END

        // -- PRIVATE PANEL
        JPanel JP_private = new JPanel(new BorderLayout(5, 5));
        JL_private = new JLabel("Chat Privata");
        JTA_private = new JTextArea();

        DefaultCaret caret_JTA_private = (DefaultCaret) JTA_private.getCaret();
        caret_JTA_private.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JTA_private.setEditable(false);
        JTA_private.setAutoscrolls(true);
        JP_private.add(JL_private, BorderLayout.PAGE_START);
        JP_private.add(new JScrollPane(JTA_private), BorderLayout.CENTER);
        // -- PRIVATE PANEL END

        JP_center.add(JP_broadcast);
        JP_center.add(JP_private);
        // - CENTER PANEL END

        // - BOTTOM PANEL
        JPanel JP_bottom = new JPanel(new GridLayout(1, 1, 5, 5));
        JTF_MsgBar = new JTextField();
        JTF_MsgBar.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER
                        && !JTF_MsgBar.getText().equals("")
                        && JTF_MsgBar.getText() != null) {
                    Main.getConnessione().sendCmdValue(ProtocolCodes.MSG, JTF_MsgBar.getText());
                    JTF_MsgBar.setText("");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        JP_bottom.add(JTF_MsgBar);
        // - BOTTOM PANEL END

        add(JP_center, BorderLayout.CENTER);
        add(JP_bottom, BorderLayout.PAGE_END);
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

    public JTextField getJTF_MsgBar() {
        return JTF_MsgBar;
    }

}