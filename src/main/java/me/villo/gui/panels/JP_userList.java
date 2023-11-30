package me.villo.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import me.villo.gui.JF_Main;
import me.villo.gui.Main;

public class JP_userList extends JPanel {

    private JButton JB_aggiorna;
    private JList<String> JL_utentiOnline;

    public JP_userList(JF_Main JF_Main) {
        setLayout(new BorderLayout(5, 5));
        JL_utentiOnline = new JList<String>();

        JB_aggiorna = new JButton("Aggiorna");
        JB_aggiorna.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.getUtente().getConnessione().updateUtentiOnline();
                String[] utenti = Main.getUtente().getConnessione().getUtentiOnline();

                JF_Main.getJP_chatMode().getJCB_destinatario().removeAll();
                JF_Main.getJP_chatMode().getJCB_destinatario().addItem("");
                for (String utente : utenti) {
                    if (!Main.getUtente().getNome().equals(utente)) {
                        JF_Main.getJP_chatMode().getJCB_destinatario().addItem(utente);
                    }
                }

                for (int i = 0; i < utenti.length; i++)
                    if (Main.getUtente().getNome().equals(utenti[i]))
                        utenti[i] = utenti[i] + " (Tu)";
                JL_utentiOnline.setListData(utenti);
            }
        });

        add(JB_aggiorna, BorderLayout.PAGE_START);
        add(JL_utentiOnline, BorderLayout.CENTER);
    }

    public JButton getJB_aggiorna() {
        return JB_aggiorna;
    }

    public JList<String> getJL_utentiOnline() {
        return JL_utentiOnline;
    }
}