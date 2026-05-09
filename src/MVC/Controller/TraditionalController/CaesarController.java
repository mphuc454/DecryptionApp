package MVC.Controller.TraditionalController;


import MVC.Model.TraditionalModel.CaesarCipher;
import MVC.View.TraditionalView.ViewCaesar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CaesarController {
    private CaesarCipher caesarCipher;
    private ViewCaesar viewCaesar;
    private CardLayout cardLayout;
    private JPanel panel;

    public CaesarController(CaesarCipher caesarCipher, ViewCaesar viewCaesar, CardLayout cardLayout, JPanel panel) {
        this.caesarCipher = caesarCipher;
        this.viewCaesar = viewCaesar;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewCaesar.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptCaesar();
            }
        });
        viewCaesar.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptCaesar();
            }
        });
        viewCaesar.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewCaesar.getGenKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKey();
            }
        });
        viewCaesar.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
    }

    public void genKey() {
        int n = new Random().nextInt(179);
        viewCaesar.getKeyField().setText(String.valueOf(n));
    }

    public void encryptCaesar(){
        try {
            String input = viewCaesar.getInputArea().getText();
            int key = Integer.parseInt(viewCaesar.getKeyField().getText());
            String result = caesarCipher.encrypt(input, key);
            viewCaesar.getOutputArea().setText(result);
        } catch (Exception e) {
            viewCaesar.getOutputArea().setText("Chưa tạo Key hoặc nhập key chưa hợp lệ");
        }
    }
    public void decryptCaesar(){
        try {
            String input = viewCaesar.getInputArea().getText();
            int key = Integer.parseInt(viewCaesar.getKeyField().getText());
            String result = caesarCipher.decrypt(input, key);
            viewCaesar.getOutputArea().setText(result);
        } catch (Exception e) {
            viewCaesar.getOutputArea().setText("Chưa tạo Key hoặc nhập key chưa hợp lệ");
        }
    }
    public void Clear(){
        viewCaesar.getKeyField().setText("");
        viewCaesar.getInputArea().setText("");
        viewCaesar.getOutputArea().setText("");
    }
}
