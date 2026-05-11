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
import java.security.NoSuchAlgorithmException;
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
                encryptAsymmetric();
            }
        });
        viewAsymmetric.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptAsymmetric();
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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("public_key.txt"));
        int choose = fileChooser.showSaveDialog(viewAsymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyPublic = Base64.getEncoder().encodeToString(asymmetricCipher.getPublicKey().getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyPublic);
            writer.close();
        }
        JOptionPane.showMessageDialog(null, "Lưu file public key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }
    public void savePrivateKey() throws NoSuchAlgorithmException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("private_key.txt"));
        int choose = fileChooser.showSaveDialog(viewAsymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String keyPriavte = Base64.getEncoder().encodeToString(asymmetricCipher.getPrivateKey().getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyPriavte);
            writer.close();
        }
        JOptionPane.showMessageDialog(null, "Lưu file public key thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }
    public void encryptAsymmetric(){
        try{
            String input = viewAsymmetric.getInputArea().getText();
            String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
            String mode = viewAsymmetric.getModeASymmetric().getSelectedItem().toString();
            String padding = viewAsymmetric.getPaddingASymmetric().getSelectedItem().toString();
            String res = asymmetricCipher.encryptBase64(input,algorithm,mode,padding);
            viewAsymmetric.getOutputArea().setText(res);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Cảnh báo",JOptionPane.WARNING_MESSAGE);

        }
    }
    public void decryptAsymmetric(){
        try{
            String input = viewAsymmetric.getInputArea().getText();
            String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
            String mode = viewAsymmetric.getModeASymmetric().getSelectedItem().toString();
            String padding = viewAsymmetric.getPaddingASymmetric().getSelectedItem().toString();
            String res = asymmetricCipher.decrypt(input,algorithm,mode,padding);
            viewAsymmetric.getOutputArea().setText(res);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Cảnh báo",JOptionPane.WARNING_MESSAGE);

        }
    }
    public void Clear(){
        viewAsymmetric.getInputPublicKey().setText("");
        viewAsymmetric.getInputPrivateKey().setText("");
        viewAsymmetric.getInputArea().setText("");
        viewAsymmetric.getOutputArea().setText("");
    }
}
