package MVC.Controller.OtherController;

import MVC.Model.OtherModel.AsymmetricCipher;
import MVC.View.OtherView.ViewAsymmetric;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricController {
    private AsymmetricCipher asymmetricCipher;
    private ViewAsymmetric viewAsymmetric;
    private CardLayout cardLayout;
    private JPanel panel;

    public AsymmetricController(AsymmetricCipher asymmetricCipher, ViewAsymmetric viewAsymmetric, CardLayout cardLayout, JPanel panel) {
        this.asymmetricCipher = asymmetricCipher;
        this.viewAsymmetric = viewAsymmetric;
        this.cardLayout = cardLayout;
        this.panel = panel;

        viewAsymmetric.getSavePK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    savePubicKey();
                } catch (NoSuchAlgorithmException ex) {
                    JOptionPane.showMessageDialog(null, "Thuật toán không thể hỗ trợ được", "Thất bại", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi không thể tải file được", "Thất bại", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        viewAsymmetric.getSavePrivateK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    savePrivateKey();
                } catch (NoSuchAlgorithmException ex) {
                    JOptionPane.showMessageDialog(null, "Thuật toán không thể hỗ trợ được", "Thất bại", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi không thể tải file được", "Thất bại", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        viewAsymmetric.getGenKey().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    genKey();
                } catch (NoSuchAlgorithmException ex) {
                    JOptionPane.showMessageDialog(null, "Thuật toán không thể hỗ trợ được", "Thất bại", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        viewAsymmetric.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewAsymmetric.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });
        viewAsymmetric.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    encryptAsymmetric();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeySpecException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewAsymmetric.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    decryptAsymmetric();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeySpecException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewAsymmetric.getEncryptFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    encryptAsymmetricFile();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeySpecException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewAsymmetric.getDecryptFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    decryptAsymmetricFile();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeySpecException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void genKey() throws NoSuchAlgorithmException {
        String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
        int key = Integer.parseInt(viewAsymmetric.getKeySizeASymmetric().getSelectedItem().toString());
        asymmetricCipher.genKey(algorithm, key);
        String keyPublic = Base64.getEncoder().encodeToString(asymmetricCipher.getPublicKey().getEncoded());
        String keyPrivate = Base64.getEncoder().encodeToString(asymmetricCipher.getPrivateKey().getEncoded());
        viewAsymmetric.getInputPublicKey().setText(keyPublic);
        viewAsymmetric.getInputPrivateKey().setText(keyPrivate);

    }
    public void savePubicKey() throws NoSuchAlgorithmException, IOException {
        if (asymmetricCipher.getPublicKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo public key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("public_key.txt"));
        int choose = fileChooser.showSaveDialog(viewAsymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyPublic = Base64.getEncoder().encodeToString(asymmetricCipher.getPublicKey().getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyPublic);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file public key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file public key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);

        }
    }
    public void savePrivateKey() throws NoSuchAlgorithmException, IOException {
        if (asymmetricCipher.getPrivateKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo priavte key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("private_key.txt"));
        int choose = fileChooser.showSaveDialog(viewAsymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyPriavte = Base64.getEncoder().encodeToString(asymmetricCipher.getPrivateKey().getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyPriavte);
            writer.close();
            JOptionPane.showMessageDialog(null, "Lưu file private key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Lưu file private key thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);

        }
    }
    public void encryptAsymmetric() throws NoSuchAlgorithmException, InvalidKeySpecException {
        publicKeyInput();

        if (asymmetricCipher.getPublicKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo public key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            String input = viewAsymmetric.getInputArea().getText();
            String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
            String mode = viewAsymmetric.getModeASymmetric().getSelectedItem().toString();
            String padding = viewAsymmetric.getPaddingASymmetric().getSelectedItem().toString();
            String res = asymmetricCipher.encryptBase64(input,algorithm,mode,padding);
            viewAsymmetric.getOutputArea().setText(res);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);

        }
    }
    public void encryptAsymmetricFile() throws NoSuchAlgorithmException, InvalidKeySpecException {
        publicKeyInput();

        if (asymmetricCipher.getPublicKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo public key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            JFileChooser fileChooser = new JFileChooser();
            int choose = fileChooser.showOpenDialog(viewAsymmetric);
            if (choose == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                File outputFile = new File(inputFile.getParent(), "encrypt_"+inputFile.getName());
                fileChooser.setSelectedFile(outputFile);
                int resultFile = fileChooser.showSaveDialog(viewAsymmetric);
                if (resultFile == JFileChooser.APPROVE_OPTION) {
                    outputFile = fileChooser.getSelectedFile();
                    String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
                    String mode = viewAsymmetric.getModeASymmetric().getSelectedItem().toString();
                    String padding = viewAsymmetric.getPaddingASymmetric().getSelectedItem().toString();
                    asymmetricCipher.encryptFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), algorithm, mode, padding);
                    JOptionPane.showMessageDialog(null, "Mã hoá file thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Đã huỷ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã hoá file thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptAsymmetric() throws NoSuchAlgorithmException, InvalidKeySpecException {
        privateKeyInput();
        if (asymmetricCipher.getPrivateKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo priavte key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            String input = viewAsymmetric.getInputArea().getText();
            String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
            String mode = viewAsymmetric.getModeASymmetric().getSelectedItem().toString();
            String padding = viewAsymmetric.getPaddingASymmetric().getSelectedItem().toString();
            String res = asymmetricCipher.decrypt(input,algorithm,mode,padding);
            viewAsymmetric.getOutputArea().setText(res);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);

        }
    }
    public void decryptAsymmetricFile() throws NoSuchAlgorithmException, InvalidKeySpecException {
        privateKeyInput();

        if (asymmetricCipher.getPrivateKey() == null){
            JOptionPane.showMessageDialog(null, "Chưa tạo priavte key", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            JFileChooser fileChooser = new JFileChooser();
            int choose = fileChooser.showOpenDialog(viewAsymmetric);
            if (choose == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                File outputFile = new File(inputFile.getParent(), "decrypt_"+inputFile.getName());
                fileChooser.setSelectedFile(outputFile);
                int resultFile = fileChooser.showSaveDialog(viewAsymmetric);
                if (resultFile == JFileChooser.APPROVE_OPTION) {
                    outputFile = fileChooser.getSelectedFile();
                    String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
                    String mode = viewAsymmetric.getModeASymmetric().getSelectedItem().toString();
                    String padding = viewAsymmetric.getPaddingASymmetric().getSelectedItem().toString();
                    asymmetricCipher.decryptFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), algorithm, mode, padding);
                    JOptionPane.showMessageDialog(null, "Giải mã file thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Đã huỷ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã file thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
public void publicKeyInput() throws NoSuchAlgorithmException, InvalidKeySpecException {
    String keyTxt = viewAsymmetric.getInputPublicKey().getText().trim();
    if (!keyTxt.isEmpty() && asymmetricCipher.getPublicKey() == null) {
        byte[] keyBytes = Base64.getDecoder().decode(keyTxt);
        String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        asymmetricCipher.setPublicKey(keyFactory.generatePublic(spec));
    }
}
public void privateKeyInput() throws NoSuchAlgorithmException, InvalidKeySpecException {
    String keyTxt = viewAsymmetric.getInputPrivateKey().getText().trim();
    if (!keyTxt.isEmpty() && asymmetricCipher.getPrivateKey() == null) {
        byte[] keyBytes = Base64.getDecoder().decode(keyTxt);
        String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        asymmetricCipher.setPrivateKey(keyFactory.generatePrivate(spec));
    }
}

    public void Clear(){
        viewAsymmetric.getInputPublicKey().setText("");
        viewAsymmetric.getInputPrivateKey().setText("");
        viewAsymmetric.getInputArea().setText("");
        viewAsymmetric.getOutputArea().setText("");
        asymmetricCipher.setPublicKey(null);
        asymmetricCipher.setPrivateKey(null);
    }
}
