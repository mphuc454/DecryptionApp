package MVC.Controller.OtherController;

import MVC.Model.OtherModel.HashCipher;
import MVC.View.OtherView.ViewHash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashController {
    private HashCipher hashCipher;
    private ViewHash viewHash;
    private CardLayout cardLayout;
    private JPanel panel;
    public HashController(HashCipher hashCipher, ViewHash viewHash, CardLayout cardLayout, JPanel panel) {
        this.hashCipher = hashCipher;
        this.viewHash = viewHash;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewHash.getBackButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
        viewHash.getEncryptButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                encryptHash();
            }
        });
        viewHash.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewHash.getEncryptFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hashFile();
            }
        });
    }

    public void encryptHash(){
        try{
            String input = viewHash.getInputArea().getText();
            if(input == null || input.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Văn bản rỗng, không thể mã hoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String algorithm = viewHash.getAlgorithmHash().getSelectedItem().toString();
            String result = hashCipher.checksum(input, algorithm);
            viewHash.getOutputArea().setText(result);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void hashFile(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            int choose = fileChooser.showOpenDialog(viewHash);
            if (choose == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                File outputFile = new File(inputFile.getParent(), "hash_"+inputFile.getName());
                fileChooser.setSelectedFile(outputFile);
                int resultFile = fileChooser.showSaveDialog(viewHash);
                if (resultFile == JFileChooser.APPROVE_OPTION) {
                    outputFile = fileChooser.getSelectedFile();
                    String algorithm = viewHash.getAlgorithmHash().getSelectedItem().toString();
                    String res = hashCipher.hash(inputFile.getAbsolutePath(),algorithm);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                    writer.write(res);
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Băm file thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Đã huỷ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Băm file thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Clear(){
        viewHash.getInputArea().setText("");
        viewHash.getOutputArea().setText("");

    }
}
