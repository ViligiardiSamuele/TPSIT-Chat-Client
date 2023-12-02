package me.villo.gui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import me.villo.ProtocolCodes;
import me.villo.gui.JF_Main;
import me.villo.gui.Main;

public class JP_chatSelector extends JPanel {

    private JRadioButton JRB_broadcast;
    private JRadioButton JRB_private;
    private ButtonGroup BG_chatMode;
    private JComboBox<String> JCB_destinatario;

    public JP_chatSelector(JF_Main JF_Main) {
        setLayout(new GridLayout(1, 3));

        JCB_destinatario = new JComboBox<String>();
        JCB_destinatario.setEnabled(false);

        BG_chatMode = new ButtonGroup();

        JRB_broadcast = new JRadioButton("Broadcast");
        JRB_broadcast.setSelected(true);
        JRB_private = new JRadioButton("Privata");

        JRB_broadcast.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JF_Main.getJP_chatArea().getJB_send().setEnabled(false);
                JF_Main.getJP_chatArea().getJTF_MsgBar().setEnabled(false);
                JF_Main.getJP_chatArea().getJTF_MsgBar().setText("In attesa della risposta del server...");

                Main.getUtente().getConnessione().sendCmdValue(ProtocolCodes.SWITCH_BROADCAST, "1");

                if (Main.getUtente().getConnessione().checkNewValueOfLMFS()) {
                    JF_Main.getJP_chatArea().getJTF_MsgBar().setText("");
                    JF_Main.getJP_chatArea().getJTF_MsgBar().setEnabled(true);
                    JF_Main.getJP_chatArea().getJB_send().setEnabled(true);
                }
            }
        });
        JRB_private.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JF_Main.getJP_chatArea().getJB_send().setEnabled(false);
                JF_Main.getJP_chatArea().getJTF_MsgBar().setEnabled(false);
                JCB_destinatario.setEnabled(true);
                JF_Main.getJP_chatArea().getJTF_MsgBar().setText("In attesa della risposta del server...");
            }
        });

        JCB_destinatario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!JCB_destinatario.getSelectedItem().equals("")) {
                    Main.getUtente().getConnessione().sendCmdValue(ProtocolCodes.SWITCH_TO_USER,
                            (String) JCB_destinatario.getSelectedItem());
                    if (Main.getUtente().getConnessione().checkNewValueOfLMFS()) {
                        JF_Main.getJP_chatArea().getJL_private()
                                .setText("Privata [" + JCB_destinatario.getSelectedItem() + "]");
                        JF_Main.getJP_chatArea().getJTA_private().setText("");
                        JF_Main.getJP_chatArea().getJTF_MsgBar().setText("");
                        JF_Main.getJP_chatArea().getJTF_MsgBar().setEnabled(true);
                        JF_Main.getJP_chatArea().getJB_send().setEnabled(true);
                    }
                }
            }
        });

        BG_chatMode.add(JRB_broadcast);
        BG_chatMode.add(JRB_private);

        add(JRB_broadcast);
        add(JRB_private);
        add(JCB_destinatario);
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

    public JComboBox<String> getJCB_destinatario() {
        return JCB_destinatario;
    }
}