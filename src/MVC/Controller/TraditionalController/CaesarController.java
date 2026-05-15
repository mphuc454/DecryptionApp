package MVC.Controller.TraditionalController;


import MVC.Model.TraditionalModel.CaesarCipher;
import MVC.View.TraditionalView.ViewCaesar;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
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
        viewCaesar.getSaveKey().addActionListener(new ActionListener() {
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

    public void genKey() {
        int n = new Random().nextInt(26);
        viewCaesar.getKeyField().setText(String.valueOf(n));
    }

    public void encryptCaesar(){
        if(viewCaesar.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewCaesar.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể mã hoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int key = Integer.parseInt(viewCaesar.getKeyField().getText());
            String result = caesarCipher.encrypt(input, key);
            viewCaesar.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptCaesar(){
        if(viewCaesar.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewCaesar.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể giải mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int key = Integer.parseInt(viewCaesar.getKeyField().getText());
            String result = caesarCipher.decrypt(input, key);
            viewCaesar.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveKey() throws IOException {
        if(viewCaesar.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("save_key.txt"));
        int choose = fileChooser.showSaveDialog(viewCaesar);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyTxt = viewCaesar.getKeyField().getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Clear(){
        viewCaesar.getKeyField().setText("");
        viewCaesar.getInputArea().setText("");
        viewCaesar.getOutputArea().setText("");
    }
}
