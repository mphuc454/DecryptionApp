package MVC.Controller.OtherController;

import MVC.Model.OtherModel.HashCipher;
import MVC.View.OtherView.ViewHash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
}
