package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.VigenereCipher;
import MVC.View.TraditionalView.ViewVigenere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VigenereController {
    private VigenereCipher vigenereCipher;
    private ViewVigenere viewVigenere;
    private CardLayout cardLayout;
    private JPanel panel;

    public VigenereController(VigenereCipher vigenereCipher, ViewVigenere viewVigenere, CardLayout cardLayout, JPanel panel) {
        this.vigenereCipher = vigenereCipher;
        this.viewVigenere = viewVigenere;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewVigenere.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptVigenere();
            }
        });
        viewVigenere.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptVigenere();
            }
        });
        viewVigenere.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewVigenere.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
    }

    public void encryptVigenere(){
        try {
            String input = viewVigenere.getInputArea().getText();
            String key = viewVigenere.getKeyField().getText();
            String repeatKey = vigenereCipher.keyGenerator(input, key);
            String result = vigenereCipher.encrypt(input, repeatKey);
            viewVigenere.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptVigenere(){
        try {
            String input = viewVigenere.getInputArea().getText();
            String key = viewVigenere.getKeyField().getText();
            String repeatKey = vigenereCipher.keyGenerator(input, key);
            String result = vigenereCipher.decrypt(input, repeatKey);
            viewVigenere.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Clear(){
        viewVigenere.getKeyField().setText("");
        viewVigenere.getInputArea().setText("");
        viewVigenere.getOutputArea().setText("");
    }
}
