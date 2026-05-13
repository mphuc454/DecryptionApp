package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.HillCipher;
import MVC.View.TraditionalView.ViewHill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HillController {
    private HillCipher hillCipher;
    private ViewHill viewHill;
    private CardLayout cardLayout;
    private JPanel panel;

    public HillController(HillCipher hillCipher, ViewHill viewHill, CardLayout cardLayout, JPanel panel) {
        this.hillCipher = hillCipher;
        this.viewHill = viewHill;
        this.cardLayout = cardLayout;
        this.panel = panel;
        viewHill.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptHill();
            }
        });
        viewHill.getDecryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptHill();
            }
        });
        viewHill.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        viewHill.getGenKey().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKey();
            }
        });
        viewHill.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "menu");
            }
        });

    }

    public void genKey() {
        Random random = new Random();
        int a = 0, b = 0, c = 0, d = 0;
        int det = 0;
        while(gcd(det, 26)!=1){
            a = random.nextInt(26);
            b = random.nextInt(26);
            c = random.nextInt(26);
            d = random.nextInt(26);
            det = ((a*d)-(b*c) % 26 + 26) % 26;
        }
        viewHill.getKeyFieldA().setText(String.valueOf(a));
        viewHill.getKeyFieldB().setText(String.valueOf(b));
        viewHill.getKeyFieldC().setText(String.valueOf(c));
        viewHill.getKeyFieldD().setText(String.valueOf(d));
    }

    private int gcd(int det, int i) {
        while(i != 0){
            int temp = i;
            i = det % i;
            det = temp;
        }
        return det;
    }

    public void encryptHill(){
        try {
            String input = viewHill.getInputArea().getText();
            int keyA = Integer.parseInt(viewHill.getKeyFieldA().getText());
            int keyB = Integer.parseInt(viewHill.getKeyFieldB().getText());
            int keyC = Integer.parseInt(viewHill.getKeyFieldC().getText());
            int keyD = Integer.parseInt(viewHill.getKeyFieldD().getText());
            int[][] key = {{keyA,keyB},{keyC,keyD}};
            String result = hillCipher.encrypt(input, key);
            viewHill.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mã hoá không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void decryptHill(){
        try {
            String input = viewHill.getInputArea().getText();
            int keyA = Integer.parseInt(viewHill.getKeyFieldA().getText());
            int keyB = Integer.parseInt(viewHill.getKeyFieldB().getText());
            int keyC = Integer.parseInt(viewHill.getKeyFieldC().getText());
            int keyD = Integer.parseInt(viewHill.getKeyFieldD().getText());
            int[][] key = {{keyA,keyB},{keyC,keyD}};
            String result =  hillCipher.decrypt(input, key);
            viewHill.getOutputArea().setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Giải mã không thành công","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Clear(){
        viewHill.getKeyFieldA().setText("");
        viewHill.getKeyFieldB().setText("");
        viewHill.getKeyFieldC().setText("");
        viewHill.getKeyFieldD().setText("");
        viewHill.getInputArea().setText("");
        viewHill.getOutputArea().setText("");
    }

}
