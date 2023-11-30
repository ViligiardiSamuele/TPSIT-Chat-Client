package me.villo.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.villo.DaemonReader;
import me.villo.ProtocolCodes;
import me.villo.gui.JF_Main;
import me.villo.gui.Main;

public class JP_chatArea extends JPanel {

    private JTextArea JTA_broadcast;
    private JTextArea JTA_private;
    private JLabel JL_broadcast;
    private JLabel JL_private;
    private JTextField JTF_MsgBar;
    private JButton JB_send;

    public JP_chatArea(JF_Main JF_Main) {
        setLayout(new BorderLayout(5, 5));
        /*
         * Content Panel
         * - Center Panel
         * -- Broadcast Panel
         * -- Private Panel
         * - Bottom Panel
         */

        // CENTER PANEL
        JPanel JP_center = new JPanel(new GridLayout(2, 1, 5, 5));

        // - BROADCAST PANEL
        JPanel JP_broadcast = new JPanel(new BorderLayout(5, 5));
        JL_broadcast = new JLabel("Broadcast chat");
        JTA_broadcast = new JTextArea();
        JTA_broadcast.setEditable(false);
        JTA_broadcast.setAutoscrolls(true);
        JP_broadcast.add(JL_broadcast, BorderLayout.PAGE_START);
        JP_broadcast.add(new JScrollPane(JTA_broadcast), BorderLayout.CENTER);
        // - BROADCAST PANEL END

        // - PRIVATE PANEL
        JPanel JP_private = new JPanel(new BorderLayout(5, 5));
        JL_private = new JLabel("Chat Privata");
        JTA_private = new JTextArea();
        JTA_private.setEditable(false);
        JTA_private.setAutoscrolls(true);
        JP_private.add(JL_private, BorderLayout.PAGE_START);
        JP_private.add(new JScrollPane(JTA_private), BorderLayout.CENTER);
        // - PRIVATE PANEL END

        JP_center.add(JP_broadcast);
        JP_center.add(JP_private);
        // CENTER PANEL END

        // PAGE_END
        JPanel JP_bottom = new JPanel(new GridLayout(2, 1, 5, 5));
        JTF_MsgBar = new JTextField();
        JB_send = new JButton("Invia");
        JB_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.getUtente().getConnessione().sendCmdValue(ProtocolCodes.MSG, JTF_MsgBar.getText());
                JTF_MsgBar.setText("");
            }
        });
        JP_bottom.add(JTF_MsgBar);
        JP_bottom.add(JB_send);
        // PAGE_END

        Main.getUtente().getConnessione().setDaemonReader(new DaemonReader(JTA_broadcast, JTA_private));
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

    public JButton getJB_send() {
        return JB_send;
    }

}