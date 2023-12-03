package me.villo.gui.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.villo.ProtocolCodes;
import me.villo.gui.Main;

public class JD_Login {

    private static JDialog jDialog;

    public JD_Login() {
        jDialog = new JDialog(new JFrame(), "Login", true);
        jDialog.setLayout(new GridLayout(3, 1, 5, 5));

        JLabel JL_info = new JLabel("Inserisci il nome");
        jDialog.add(JL_info);

        JTextField JTF_name = new JTextField();
        jDialog.add(JTF_name);

        JPanel JP_button = new JPanel(new GridLayout(1, 3));
        JP_button.add(new JLabel());
        JButton JB_Login = new JButton("Login");
        JB_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Verifica che non siano presenti caratteri non consentiti
                Matcher matcher = Pattern.compile("[/:*?<>|]").matcher(JTF_name.getText().trim());

                if (JTF_name.getText().trim().length() < 2) {
                    JL_info.setText("ERRORE: il nome deve essere lungo almeno 2 caratteri");
                } else if (JTF_name.getText().trim().equals("BROADCAST") || matcher.find()) {
                    JL_info.setText("ERRORE: Questo nome non è consentito");
                } else {
                    try {
                        JL_info.setText("In attesa del server...");
                        Main.getUtente().setNome(JTF_name.getText());
                        Main.getConnessione().sendCmdValue(ProtocolCodes.SWITCH_BROADCAST, "1");
                        //if (Main.getConnessione().checkNewValueOfLMFS()) {
                            JD_Login.jDialog.setVisible(false);
                        //}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        jDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Main.getConnessione().close();
                System.exit(0);
            }
        });

        JP_button.add(JB_Login);
        JP_button.add(new JLabel());

        jDialog.add(JP_button);

        jDialog.setSize(340, 100);
        jDialog.setLocationRelativeTo(null);
        jDialog.setResizable(false);
        jDialog.setVisible(true);
    }
}