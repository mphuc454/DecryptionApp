package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.VigenereCipher;
import MVC.View.TraditionalView.ViewVigenere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

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
        viewVigenere.getGenKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKey();
            }
        });
        viewVigenere.getSaveKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveKey();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void  genKey() {
        Random rand = new Random();
        int len = 12;
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < len; i++){
            char c = (char) ('A' + rand.nextInt(26));
            key.append(c);
        }
        viewVigenere.getKeyField().setText(key.toString());
    }
    public void encryptVigenere(){
        if(viewVigenere.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewVigenere.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể mã hoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String key = viewVigenere.getKeyField().getText();
            String repeatKey = vigenereCipher.keyGenerator(input, key);
            String result = vigenereCipher.encrypt(input, repeatKey);
            viewVigenere.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptVigenere(){
        if(viewVigenere.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewVigenere.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể giải mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String key = viewVigenere.getKeyField().getText();
            String repeatKey = vigenereCipher.keyGenerator(input, key);
            String result = vigenereCipher.decrypt(input, repeatKey);
            viewVigenere.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveKey() throws IOException {
        if(viewVigenere.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("save_key.txt"));
        int choose = fileChooser.showSaveDialog(viewVigenere);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyTxt = viewVigenere.getKeyField().getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Clear(){
        viewVigenere.getKeyField().setText("");
        viewVigenere.getInputArea().setText("");
        viewVigenere.getOutputArea().setText("");
    }
}
