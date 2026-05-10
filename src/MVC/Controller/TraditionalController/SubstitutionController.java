package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.SubstitutionCipher;
import MVC.View.TraditionalView.ViewSubstitution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }
    public void genKey() {
        String res = substitutionCipher.randomMappingAlphabet();
        viewSubstitution.getKeyField().setText(res);
    }

    public void encryptSubstitution(){
        try{
            String input = viewSubstitution.getInputArea().getText();
            String res = substitutionCipher.encrypt(input);
            viewSubstitution.getOutputArea().setText(res);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Chưa tạo key hoặc key không hợp lệ","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void decryptSubstitution(){
        try{
            String input = viewSubstitution.getInputArea().getText();
            String res = substitutionCipher.decrypt(input);
            viewSubstitution.getOutputArea().setText(res);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Chưa tạo key hoặc key không hợp lệ","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void Clear(){
        viewSubstitution.getInputArea().setText("");
        viewSubstitution.getOutputArea().setText("");
        viewSubstitution.getKeyField().setText("");
    }
}
