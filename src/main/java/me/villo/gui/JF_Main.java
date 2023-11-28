package me.villo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import me.villo.gui.components.JP_chatMode;
import me.villo.gui.components.JP_chats;
import me.villo.gui.components.JP_bar;

public class JF_Main extends JFrame {

    private JP_chatMode JP_chatMode;
    private JP_chats JP_chats;
    private JP_bar JP_bar;

    public JF_Main() {
        super("Test");
        setLayout(new GridLayout(3,1));

        JP_chatMode = new JP_chatMode();
        JP_chats = new JP_chats();
        JP_bar = new JP_bar();
        JP_chatMode.;

        add(JP_chatMode, BorderLayout.PAGE_START);
        add(JP_chats, BorderLayout.CENTER);
        add(JP_bar, BorderLayout.PAGE_END);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JP_chatMode getJP_chatMode() {
        return JP_chatMode;
    }

    public JP_chats getJP_chats() {
        return JP_chats;
    }

    public JP_bar getJP_bar() {
        return JP_bar;
    }

}
