package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.AffineCipher;
import MVC.View.TraditionalView.ViewAffine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class AffineController {
    private AffineCipher affineCipher;
    private ViewAffine viewAffine;
    private CardLayout cardLayout;
    private JPanel panel;

    public AffineController(AffineCipher affineCipher, ViewAffine viewAffine, CardLayout cardLayout, JPanel panel) {
        this.affineCipher = affineCipher;
        this.viewAffine = viewAffine;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewAffine.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptAffine();
            }
        });
        viewAffine.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptAffine();
            }
        });
        viewAffine.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewAffine.getGenKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKey();
            }
        });
        viewAffine.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
        viewAffine.getSaveKey().addActionListener(new ActionListener() {
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
        int a = 0;
        while(gcd(a, 26) != 1){
            a = new Random().nextInt(26);
        }
        int b = new Random().nextInt(26);
        viewAffine.getKeyFieldA().setText(String.valueOf(a));
        viewAffine.getKeyFieldB().setText(String.valueOf(b));
    }
    private int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    public void encryptAffine(){
        if(viewAffine.getKeyFieldA().getText().isEmpty() || viewAffine.getKeyFieldB().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewAffine.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể mã hoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int keyA = Integer.parseInt(viewAffine.getKeyFieldA().getText());
            int keyB = Integer.parseInt(viewAffine.getKeyFieldB().getText());
            String result = affineCipher.encrypt(input, keyA, keyB);
            viewAffine.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptAffine(){
        if(viewAffine.getKeyFieldA().getText().isEmpty() || viewAffine.getKeyFieldB().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewAffine.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể giải mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int keyA = Integer.parseInt(viewAffine.getKeyFieldA().getText());
            int keyB = Integer.parseInt(viewAffine.getKeyFieldB().getText());
            String result = affineCipher.decrypt(input, keyA, keyB);
            viewAffine.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveKey() throws IOException {
        if(viewAffine.getKeyFieldA().getText().isEmpty() || viewAffine.getKeyFieldB().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("save_key.txt"));
        int choose = fileChooser.showSaveDialog(viewAffine);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyData = viewAffine.getKeyFieldA().getText().trim() + " " + viewAffine.getKeyFieldB().getText().trim();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyData);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Clear(){
        viewAffine.getKeyFieldA().setText("");
        viewAffine.getKeyFieldB().setText("");
        viewAffine.getInputArea().setText("");
        viewAffine.getOutputArea().setText("");
    }
}
