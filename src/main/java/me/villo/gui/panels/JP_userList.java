package me.villo.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import me.villo.ProtocolCodes;
import me.villo.gui.Main;

/**
 * @author Viligiardi Samuele
 */
public class JP_userList extends JPanel implements ListSelectionListener {

    private JList<String> JL_utentiOnline;

    public JP_userList() {
        setLayout(new BorderLayout(5, 5));
        JL_utentiOnline = new JList<String>();
        JL_utentiOnline.addListSelectionListener(this);
        add(JL_utentiOnline, BorderLayout.CENTER);
    }

    public JList<String> getJL_utentiOnline() {
        return JL_utentiOnline;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            if (!JL_utentiOnline.getSelectedValue().contains(" (Tu)")) {
                Main.getJF_Main().getJP_chatMode().getJRB_broadcast().setSelected(false);
                Main.getConnessione().sendCmdValue(ProtocolCodes.SWITCH_TO_USER,
                        JL_utentiOnline.getSelectedValue());
                if (Main.getConnessione().checkNewValueOfLMFS()) {
                    Main.getJF_Main().getJP_chatArea().getJL_private()
                            .setText("Privata [" + JL_utentiOnline.getSelectedValue() + "]");
                    Main.getJF_Main().getJP_chatArea().getJTA_private().setText(""); // svuola la chat privata
                    Main.getConnessione().sendCmdValue(ProtocolCodes.CHAT_REQUEST, "1");
                    Main.getJF_Main()
                            .setTitle("Chat - " + Main.getUtente().getNome() + " -> "
                                    + JL_utentiOnline.getSelectedValue());
                    Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setText("");
                    Main.getJF_Main().getJP_chatArea().getJTF_MsgBar().setEnabled(true);
                }
            }
        } catch (Exception ex) {
        }

    }
}