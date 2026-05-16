package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.SubstitutionCipher;
import MVC.View.TraditionalView.ViewSubstitution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SubstitutionController {
    private SubstitutionCipher substitutionCipher;
    private ViewSubstitution viewSubstitution;
    private CardLayout cardLayout;
    private JPanel panel;

    public SubstitutionController(SubstitutionCipher substitutionCipher, ViewSubstitution viewSubstitution, CardLayout cardLayout, JPanel panel) {
        this.substitutionCipher = substitutionCipher;
        this.viewSubstitution = viewSubstitution;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewSubstitution.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptSubstitution();
            }
        });
        viewSubstitution.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptSubstitution();
            }
        });
        viewSubstitution.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewSubstitution.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
        viewSubstitution.getGenKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKey();
            }
        });
        viewSubstitution.getSaveKey().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveKey();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewSubstitution.getSaveResult().addActionListener(new ActionListener() {
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
        String res = substitutionCipher.randomMappingAlphabet();
        substitutionCipher.setMappingAlphabet(res);
        viewSubstitution.getKeyField().setText(res);
    }

    public void encryptSubstitution(){
        if(viewSubstitution.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            String input = viewSubstitution.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể mã hoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String res = substitutionCipher.encrypt(input);
            viewSubstitution.getOutputArea().setText(res);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptSubstitution(){
        if(viewSubstitution.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            String input = viewSubstitution.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể giải mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String res = substitutionCipher.decrypt(input);
            viewSubstitution.getOutputArea().setText(res);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveKey() throws IOException {
        if(viewSubstitution.getKeyField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("save_key.txt"));
        int choose = fileChooser.showSaveDialog(viewSubstitution);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyTxt = viewSubstitution.getKeyField().getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveResult() throws IOException {
        if(viewSubstitution.getOutputArea().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Văn bản rỗng chưa có kết quả", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("result.txt"));
        int choose = fileChooser.showSaveDialog(viewSubstitution);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyTxt = viewSubstitution.getOutputArea().getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void Clear(){
        viewSubstitution.getInputArea().setText("");
        viewSubstitution.getOutputArea().setText("");
        viewSubstitution.getKeyField().setText("");
        substitutionCipher.setMappingAlphabet("");
    }
}
