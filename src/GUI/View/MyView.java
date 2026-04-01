package GUI.View;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    JPanel panelTitle1 = new JPanel();
    JPanel panelTitle2 = new JPanel();
    JPanel panelButton1 = new JPanel();
    JLabel label1 = new JLabel("Mã hoá truyền thống");



    public MyView(){
        this.setTitle("App Encryption Basic");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        panelTitle1.setBackground(Color.red);
        panelTitle2.setBackground(Color.green);
        panelTitle1.add(label1);
        panelButton1.add(new JButton("AffineCipher"));
        panelButton1.add(new JButton("CaesarCipher"));
        panelButton1.add(new JButton("HillCipher"));
        panelButton1.add(new JButton("Substitution"));
        panelButton1.add(new JButton("VigenereCipher"));
        panelTitle1.setPreferredSize(new Dimension(200, 50));
        this.add(panelTitle1, BorderLayout.WEST);
        this.add(panelTitle2, BorderLayout.WEST);
        this.add(panelButton1, BorderLayout.CENTER);

    }

}
