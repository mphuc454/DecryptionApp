package MVC.Controller.TraditionalController;


import MVC.Model.TraditionalModel.TranspositionCipher;
import MVC.View.TraditionalView.ViewTransposition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        viewTransposition.getSaveKey().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveKey();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewTransposition.getSaveResult().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveResult();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void genKey() {
        int n = new Random().nextInt(26);
        viewTransposition.getKeyField().setText(String.valueOf(n));
    }

    public void encryptTransposition(){
        if(viewTransposition.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewTransposition.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể mã hoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int key = Integer.parseInt(viewTransposition.getKeyField().getText());
            String result = transpositionCipher.encrypt(input, key);
            viewTransposition.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptTransposition(){
        if(viewTransposition.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewTransposition.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể giải mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int key = Integer.parseInt(viewTransposition.getKeyField().getText());
            String result = transpositionCipher.decrypt(input, key);
            viewTransposition.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveKey() throws IOException {
        if(viewTransposition.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("save_key.txt"));
        int choose = fileChooser.showSaveDialog(viewTransposition);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyTxt = viewTransposition.getKeyField().getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveResult() throws IOException {
        if(viewTransposition.getOutputArea().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Văn bản rỗng chưa có kết quả", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("result.txt"));
        int choose = fileChooser.showSaveDialog(viewTransposition);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyTxt = viewTransposition.getOutputArea().getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void Clear(){
        viewTransposition.getKeyField().setText("");
        viewTransposition.getInputArea().setText("");
        viewTransposition.getOutputArea().setText("");
    }
}
