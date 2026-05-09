package MVC.Controller.TraditionalController;


import MVC.Model.TraditionalModel.TranspositionCipher;
import MVC.View.TraditionalView.ViewTransposition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TranspositionController {
    private TranspositionCipher transpositionCipher;
    private ViewTransposition viewTransposition;
    private CardLayout cardLayout;
    private JPanel panel;

    public TranspositionController(TranspositionCipher transpositionCipher, ViewTransposition viewTransposition, CardLayout cardLayout, JPanel panel) {
        this.transpositionCipher = transpositionCipher;
        this.viewTransposition = viewTransposition;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewTransposition.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptTransposition();
            }
        });
        viewTransposition.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptTransposition();
            }
        });
        viewTransposition.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewTransposition.getGenKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKey();
            }
        });
        viewTransposition.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
    }
    public void genKey() {
        int n = new Random().nextInt(179);
        viewTransposition.getKeyField().setText(String.valueOf(n));
    }

    public void encryptTransposition(){
        try {
            String input = viewTransposition.getInputArea().getText();
            int key = Integer.parseInt(viewTransposition.getKeyField().getText());
            String result = transpositionCipher.encrypt(input, key);
            viewTransposition.getOutputArea().setText(result);
        } catch (Exception e) {
            viewTransposition.getOutputArea().setText("Chưa tạo Key hoặc nhập key chưa hợp lệ");
        }
    }
    public void decryptTransposition(){
        try {
            String input = viewTransposition.getInputArea().getText();
            int key = Integer.parseInt(viewTransposition.getKeyField().getText());
            String result = transpositionCipher.decrypt(input, key);
            viewTransposition.getOutputArea().setText(result);
        } catch (Exception e) {
            viewTransposition.getOutputArea().setText("Chưa tạo Key hoặc nhập key chưa hợp lệ");
        }
    }
    public void Clear(){
        viewTransposition.getKeyField().setText("");
        viewTransposition.getInputArea().setText("");
        viewTransposition.getOutputArea().setText("");
    }
}
