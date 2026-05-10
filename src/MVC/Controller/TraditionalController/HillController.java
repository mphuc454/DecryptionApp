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
        int a = new Random().nextInt(179);
        int b = new Random().nextInt(179);
        int c = new Random().nextInt(179);
        int d = new Random().nextInt(179);
        viewHill.getKeyFieldA().setText(String.valueOf(a));
        viewHill.getKeyFieldB().setText(String.valueOf(b));
        viewHill.getKeyFieldC().setText(String.valueOf(c));
        viewHill.getKeyFieldD().setText(String.valueOf(d));
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
            viewHill.getOutputArea().setText("Chưa tạo Key hoặc nhập key chưa hợp lệ");
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
            viewHill.getOutputArea().setText("Chưa tạo Key hoặc nhập key chưa hợp lệ");
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
