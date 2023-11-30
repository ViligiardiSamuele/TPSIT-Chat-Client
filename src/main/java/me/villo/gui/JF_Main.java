package me.villo.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import me.villo.gui.panels.JP_chatSelector;
import me.villo.gui.panels.JP_userList;
import me.villo.gui.panels.JP_chatArea;
import me.villo.gui.frames.JD_Login;

public class JF_Main extends JFrame {

    private JP_chatSelector JP_chatMode;
    private JP_chatArea JP_chatArea;
    private JP_userList JP_userList;

    public JF_Main() {
        super("Test");
        setLayout(new BorderLayout(5, 5));
        JP_chatArea = new JP_chatArea(this);
        JP_chatArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JP_userList = new JP_userList(this);
        JP_userList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JP_chatMode = new JP_chatSelector(this);
        JP_chatMode.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(JP_chatMode, BorderLayout.PAGE_START);
        add(JP_chatArea, BorderLayout.CENTER);
        add(JP_userList, BorderLayout.LINE_END);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // OnClose
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.getUtente().getConnessione().close();
                e.getWindow().dispose();
                System.exit(0);
            }
        });

        setVisible(true);
        new JD_Login();
    }

    public JP_chatSelector getJP_chatMode() {
        return JP_chatMode;
    }

    public JP_chatArea getJP_chatArea() {
        return JP_chatArea;
    }

    public JP_userList getJP_userList() {
        return JP_userList;
    }

}