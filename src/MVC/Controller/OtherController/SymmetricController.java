package MVC.Controller.OtherController;

import MVC.Model.OtherModel.SymmetricCipher;
import MVC.View.OtherView.ViewSymmetric;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

public class SymmetricController {
    private SymmetricCipher symmetricCipher;
    private ViewSymmetric viewSymmetric;
    private CardLayout cardLayout;
    private JPanel panel;

    public SymmetricController(SymmetricCipher symmetricCipher, ViewSymmetric viewSymmetric, CardLayout cardLayout, JPanel panel) {
        this.symmetricCipher = symmetricCipher;
        this.viewSymmetric = viewSymmetric;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewSymmetric.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptSymmetric();
            }
        });
        viewSymmetric.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptSymmetric();
            }
        });
        viewSymmetric.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewSymmetric.getGenKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    genKey();
                } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
                    JOptionPane.showMessageDialog(null, "Thuật toán không thể hỗ trợ được", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        viewSymmetric.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
        viewSymmetric.getEncryptFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptSymmetricFile();
            }
        });
        viewSymmetric.getDecryptFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptSymmetricFile();
            }
        });
        viewSymmetric.getSaveKeyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveKey();
                } catch (NoSuchAlgorithmException | IOException | NoSuchProviderException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi không thể tải file được", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public void genKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
        int keySize = Integer.parseInt(viewSymmetric.getKeySizeSymmetric().getSelectedItem().toString());
        SecretKey secretKey = symmetricCipher.genKey(algorithm, keySize);
        String key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        symmetricCipher.genIV(algorithm);
        viewSymmetric.getKeyField().setText(key);
    }
    public void saveKey() throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        keyInput();

        if(symmetricCipher.getKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("secret_key.txt"));
        int choose = fileChooser.showSaveDialog(viewSymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            SecretKey secretKey = symmetricCipher.getKey();
            String keyTxt = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void encryptSymmetric(){
        keyInput();

        if(symmetricCipher.getKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewSymmetric.getInputArea().getText();
            String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
            String mode = viewSymmetric.getModeSymmetric().getSelectedItem().toString();
            String padding = viewSymmetric.getPaddingSymmetric().getSelectedItem().toString();
            byte[] re = symmetricCipher.encrypt(input, algorithm, mode, padding);
            String result = Base64.getEncoder().encodeToString(re);
            viewSymmetric.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void encryptSymmetricFile(){
        keyInput();

        if(symmetricCipher.getKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            JFileChooser fileChooser = new JFileChooser();
            int choose = fileChooser.showOpenDialog(viewSymmetric);
            if (choose == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                File outputFile = new File(inputFile.getParent(), "encrypt_"+inputFile.getName());
                fileChooser.setSelectedFile(outputFile);
                int resultFile = fileChooser.showSaveDialog(viewSymmetric);
                if (resultFile == JFileChooser.APPROVE_OPTION) {
                    outputFile = fileChooser.getSelectedFile();
                    String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
                    String mode = viewSymmetric.getModeSymmetric().getSelectedItem().toString();
                    String padding = viewSymmetric.getPaddingSymmetric().getSelectedItem().toString();
                    symmetricCipher.encryptFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), algorithm, mode, padding);
                    JOptionPane.showMessageDialog(null, "Mã hoá file thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Đã huỷ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã hoá file thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptSymmetric(){
        keyInput();

        if(symmetricCipher.getKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String input = viewSymmetric.getInputArea().getText();
            String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
            String mode = viewSymmetric.getModeSymmetric().getSelectedItem().toString();
            String padding = viewSymmetric.getPaddingSymmetric().getSelectedItem().toString();
            byte[] encrypted = Base64.getDecoder().decode(input);
            String result = symmetricCipher.decrypt(encrypted, algorithm, mode, padding);
            viewSymmetric.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptSymmetricFile(){
        keyInput();
        if(symmetricCipher.getKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            JFileChooser fileChooser = new JFileChooser();
            int choose = fileChooser.showOpenDialog(viewSymmetric);
            if (choose == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                File outputFile = new File(inputFile.getParent(), "decrypt_"+inputFile.getName());
                fileChooser.setSelectedFile(outputFile);
                int resultFile = fileChooser.showSaveDialog(viewSymmetric);
                if (resultFile == JFileChooser.APPROVE_OPTION) {
                    outputFile = fileChooser.getSelectedFile();
                    String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
                    String mode = viewSymmetric.getModeSymmetric().getSelectedItem().toString();
                    String padding = viewSymmetric.getPaddingSymmetric().getSelectedItem().toString();
                    symmetricCipher.decryptFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), algorithm, mode, padding);
                    JOptionPane.showMessageDialog(null, "Giải mã file thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Đã huỷ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã file thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void keyInput(){
        String keyTxt = viewSymmetric.getKeyField().getText().trim();
        if (!keyTxt.isEmpty() && symmetricCipher.getKey() == null) {
            byte[] keyBytes = Base64.getDecoder().decode(keyTxt);
            String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
            SecretKey secretKey = new SecretKeySpec(keyBytes, algorithm);
            symmetricCipher.loadKey(secretKey);
            symmetricCipher.genIV(algorithm);
        }
    }

    public void Clear(){
        viewSymmetric.getKeyField().setText("");
        viewSymmetric.getInputArea().setText("");
        viewSymmetric.getOutputArea().setText("");
        symmetricCipher.loadKey(null);
        symmetricCipher.setIV(null);
    }

}
