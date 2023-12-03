package me.villo.gui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import me.villo.ProtocolCodes;
import me.villo.gui.Main;

public class JP_chatSelector extends JPanel {

    private JRadioButton JRB_broadcast;
    //private JRadioButton JRB_private;
    private ButtonGroup BG_chatMode;

    public JP_chatSelector() {
        setLayout(new GridLayout(1, 1));

        BG_chatMode = new ButtonGroup();

        JRB_broadcast = new JRadioButton("Broadcast");
        JRB_broadcast.setSelected(true);
        //JRB_private = new JRadioButton("Privata");

        JRB_broadcast.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setEnabled(false);
                Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setText("In attesa del server...");

                Main.getConnessione().sendCmdValue(ProtocolCodes.SWITCH_BROADCAST, "1");

                if (Main.getConnessione().checkNewValueOfLMFS()) {
                    Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setText("");
                    Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setEnabled(true);
                }
                Main.getJF_Main()
                        .setTitle("Chat - " + Main.getUtente().getNome() + " -> Broadcast");
                        Main.getJF_Main().getJP_userList().getJL_utentiOnline().clearSelection();
            }
        });
        /*
        JRB_private.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.getJF_Main().getJP_userList().setVisible(true);
                    Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setEnabled(false);
                    Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setText("Selezione un destinatario...");
            }
        });*/

        //BG_chatMode.add(JRB_broadcast);
        //BG_chatMode.add(JRB_private);

        add(JRB_broadcast);
        //add(JRB_private);
    }

    public JRadioButton getJRB_broadcast() {
        return JRB_broadcast;
    }

    /*
    public JRadioButton getJRB_private() {
        return JRB_private;
    }*/

    public ButtonGroup getBG_chatMode() {
        return BG_chatMode;
    }
}