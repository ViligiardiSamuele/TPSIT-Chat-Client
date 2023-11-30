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
        JL_utentiOnline = new JList<String>(Main.getUtente().getConnessione().getUtentiOnline());

        JB_aggiorna = new JButton("Aggiorna");
        JB_aggiorna.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JL_utentiOnline = new JList<String>(Main.getUtente().getConnessione().getUtentiOnline());
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