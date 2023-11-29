package me.villo.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SendListener implements ActionListener{
    
    private JButton button;

    public SendListener(JButton button){
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
    }
    
}
