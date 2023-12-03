package me.villo.gui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import me.villo.ProtocolCodes;
import me.villo.gui.Main;

/**
 * @author Viligiardi Samuele
 */
public class JP_chatSelector extends JPanel {

    private JRadioButton JRB_broadcast;
    private ButtonGroup BG_chatMode;

    public JP_chatSelector() {
        setLayout(new GridLayout(1, 1));

        BG_chatMode = new ButtonGroup();

        JRB_broadcast = new JRadioButton("Broadcast");
        JRB_broadcast.setSelected(true);

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
        add(JRB_broadcast);
    }

    public JRadioButton getJRB_broadcast() {
        return JRB_broadcast;
    }

    public ButtonGroup getBG_chatMode() {
        return BG_chatMode;
    }
}