package MVC.Controller.AsymmetricController;

import MVC.Model.AsymmetricModel.AsymmetricCipher;
import MVC.View.AsymmetricView.ViewAsymmetric;

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
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewAsymmetric.getSavePrivateK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    savePrivateKey();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void genKey() throws NoSuchAlgorithmException {
        String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
        int key = Integer.parseInt(viewAsymmetric.getKeySizeASymmetric().getSelectedItem().toString());
        asymmetricCipher.genKey(algorithm, key);
    }
    public void savePubicKey() throws NoSuchAlgorithmException, IOException {
        String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
        int key = Integer.parseInt(viewAsymmetric.getKeySizeASymmetric().getSelectedItem().toString());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("public_key.txt"));
        int choose = fileChooser.showSaveDialog(viewAsymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            asymmetricCipher.genKey(algorithm, key);
            String keyPublic = Base64.getEncoder().encodeToString(asymmetricCipher.getPublicKey().getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyPublic);
            writer.close();
        }
        JOptionPane.showConfirmDialog(null, "Lưu file public key thành công", "Thành công", JOptionPane.OK_OPTION);
    }
    public void savePrivateKey() throws NoSuchAlgorithmException, IOException {
        String algorithm = viewAsymmetric.getAlgorithmASymmetric().getSelectedItem().toString();
        int key = Integer.parseInt(viewAsymmetric.getKeySizeASymmetric().getSelectedItem().toString());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("private_key.txt"));
        int choose = fileChooser.showSaveDialog(viewAsymmetric);
        if (choose == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            asymmetricCipher.genKey(algorithm, key);
            String keyPriavte = Base64.getEncoder().encodeToString(asymmetricCipher.getPrivateKey().getEncoded());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(keyPriavte);
            writer.close();
        }
        JOptionPane.showConfirmDialog(null, "Lưu file public key thành công", "Thành công", JOptionPane.OK_OPTION);
    }
}
