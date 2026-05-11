package MVC.Controller.SymmetricController;

import MVC.Model.SymmetricModel.SymmetricCipher;
import MVC.View.SymmetricView.ViewSymmetric;

import javax.crypto.SecretKey;
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
                    throw new RuntimeException(ex);
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
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void genKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
        int keySize = Integer.parseInt(viewSymmetric.getKeySizeSymmetric().getSelectedItem().toString());
        symmetricCipher.genKey(algorithm, keySize);
        if(symmetricCipher.genIV(algorithm) == null){
            symmetricCipher.genIV(algorithm);
        }
    }
    public void saveKey() throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
        int keySize = Integer.parseInt(viewSymmetric.getKeySizeSymmetric().getSelectedItem().toString());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("secret_key.txt"));
        int choose = fileChooser.showSaveDialog(viewSymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            SecretKey secretKey = symmetricCipher.genKey(algorithm, keySize);
            String keyTxt = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyTxt);
            writer.close();
        }
        JOptionPane.showConfirmDialog(null, "Lưu file key thành công", "Thành công", JOptionPane.OK_OPTION);
    }
    public void encryptSymmetric(){
        try {
            String input = viewSymmetric.getInputArea().getText();
            String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
            String mode = viewSymmetric.getModeSymmetric().getSelectedItem().toString();
            String padding = viewSymmetric.getPaddingSymmetric().getSelectedItem().toString();
            byte[] re = symmetricCipher.encrypt(input, algorithm, mode, padding);
            String result = Base64.getEncoder().encodeToString(re);
            viewSymmetric.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chưa tạo key hoặc key không hợp lệ","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void encryptSymmetricFile(){
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
                }
            }
            JOptionPane.showConfirmDialog(null, "Mã hoá file thành công", "Thành công", JOptionPane.OK_OPTION);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã hoá file thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptSymmetric(){
        try {
            String input = viewSymmetric.getInputArea().getText();
            String algorithm = viewSymmetric.getAlgorithmSymmetric().getSelectedItem().toString();
            String mode = viewSymmetric.getModeSymmetric().getSelectedItem().toString();
            String padding = viewSymmetric.getPaddingSymmetric().getSelectedItem().toString();
            byte[] encrypted = Base64.getDecoder().decode(input);
            String result = symmetricCipher.decrypt(encrypted, algorithm, mode, padding);
            viewSymmetric.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chưa tạo key hoặc key không hợp lệ","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void decryptSymmetricFile(){
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
                }
            }
            JOptionPane.showConfirmDialog(null, "Giải mã file thành công", "Thành công", JOptionPane.OK_OPTION);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã file thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Clear(){
        viewSymmetric.getInputArea().setText("");
        viewSymmetric.getOutputArea().setText("");
    }

}
