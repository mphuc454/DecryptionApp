package GUI.View;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    JPanel panelMain = new JPanel();
    JPanel panelTraditional = new JPanel();
    JPanel panelSymmetric = new JPanel();
    JPanel panelAsymmetric = new JPanel();
    JPanel panelHash = new JPanel();
    final int maxWidth = Integer.MAX_VALUE;
    final int maxHeight = 300;



    public MyView(){
        this.setTitle("App Encryption Basic");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.add(panelTraditional);
        panelMain.add(Box.createVerticalStrut(10));
        panelMain.add(panelSymmetric);
        panelMain.add(Box.createVerticalStrut(10));
        panelMain.add(panelAsymmetric);
        panelMain.add(Box.createVerticalStrut(10));
        panelMain.add(panelHash);

        panelTraditional.setLayout(new BoxLayout(panelTraditional, BoxLayout.X_AXIS));
        panelSymmetric.setLayout(new BoxLayout(panelSymmetric, BoxLayout.X_AXIS));
        panelAsymmetric.setLayout(new BoxLayout(panelAsymmetric, BoxLayout.X_AXIS));
        panelHash.setLayout(new BoxLayout(panelHash, BoxLayout.X_AXIS));

        panelTraditional.setBorder(BorderFactory.createTitledBorder("Mã hoá truyền thống"));
        panelTraditional.setMaximumSize(new Dimension(maxWidth, maxHeight));
        panelTraditional.add(new JButton("AffineCipher"));
        panelTraditional.add(Box.createHorizontalStrut(10));
        panelTraditional.add(new JButton("CaesarCipher"));
        panelTraditional.add(Box.createHorizontalStrut(10));
        panelTraditional.add(new JButton("HillCipher"));
        panelTraditional.add(Box.createHorizontalStrut(10));
        panelTraditional.add(new JButton("Substitution"));
        panelTraditional.add(Box.createHorizontalStrut(10));
        panelTraditional.add(new JButton("VigenereCipher"));

        panelSymmetric.setBorder(BorderFactory.createTitledBorder("Mã hoá Đối xứng"));
        panelSymmetric.setMaximumSize(new Dimension(maxWidth, maxHeight));
        panelSymmetric.add(new JButton("AES"));
        panelSymmetric.add(Box.createHorizontalStrut(10));
        panelSymmetric.add(new JButton("DES"));

        panelAsymmetric.setBorder(BorderFactory.createTitledBorder("Mã hoá Bất Đối xứng"));
        panelAsymmetric.setMaximumSize(new Dimension(maxWidth, maxHeight));
        panelAsymmetric.add(new JButton("RSA"));

        panelHash.setBorder(BorderFactory.createTitledBorder("Hàm băm"));
        panelHash.setMaximumSize(new Dimension(maxWidth, maxHeight));
        panelHash.add(new JButton("SHA"));
        panelHash.add(Box.createHorizontalStrut(10));
        panelHash.add(new JButton("MD5"));



        this.add(panelMain, BorderLayout.CENTER);









    }

}
