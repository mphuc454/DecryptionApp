package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.AffineCipher;
import MVC.View.TraditionalView.ViewAffine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    }

    public void genKey() {
        int n = new Random().nextInt(26);
        int m = new Random().nextInt(26);
        viewAffine.getKeyFieldA().setText(String.valueOf(n));
        viewAffine.getKeyFieldB().setText(String.valueOf(m));
    }

    public void encryptAffine(){
        try {
            String input = viewAffine.getInputArea().getText();
            int keyA = Integer.parseInt(viewAffine.getKeyFieldA().getText());
            int keyB = Integer.parseInt(viewAffine.getKeyFieldB().getText());
            String result = affineCipher.encrypt(input, keyA, keyB);
            viewAffine.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chưa tạo key hoặc key không hợp lệ","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void decryptAffine(){
        try {
            String input = viewAffine.getInputArea().getText();
            int keyA = Integer.parseInt(viewAffine.getKeyFieldA().getText());
            int keyB = Integer.parseInt(viewAffine.getKeyFieldB().getText());
            String result = affineCipher.decrypt(input, keyA, keyB);
            viewAffine.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chưa tạo key hoặc key không hợp lệ","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void Clear(){
        viewAffine.getKeyFieldA().setText("");
        viewAffine.getKeyFieldB().setText("");
        viewAffine.getInputArea().setText("");
        viewAffine.getOutputArea().setText("");
    }
}
