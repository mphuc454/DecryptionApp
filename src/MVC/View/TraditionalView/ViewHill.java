package MVC.View.TraditionalView;

import javax.swing.*;
import java.awt.*;

public class ViewHill extends JPanel {
    private JButton btnBack;
    private JTextField txtKeyA;
    private JTextField txtKeyB;
    private JTextField txtKeyC;
    private JTextField txtKeyD;
    private JTextArea txtInput;
    private JTextArea txtOutput;
    private JButton btnEncrypt;
    private JButton btnDecrypt;
    private JButton btnClear;
    private JButton btnGenKey;
    private JButton btnSaveKey;
    private JButton btnSaveResult;


    public ViewHill(){
        this.setLayout(new BorderLayout(10, 10));

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel inputPan = new JPanel(new BorderLayout(5, 5));
        JLabel titleInput = new JLabel("Nhập văn bản");
        txtInput = new JTextArea();
        inputPan.add(titleInput, BorderLayout.NORTH);
        inputPan.add(new JScrollPane(txtInput), BorderLayout.CENTER);
        JPanel outputPan = new JPanel(new BorderLayout(5, 5));
        JLabel titleOutput = new JLabel("Kết quả");
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        outputPan.add(titleOutput, BorderLayout.NORTH);
        outputPan.add(new JScrollPane(txtOutput), BorderLayout.CENTER);
        centerPanel.add(inputPan);
        centerPanel.add(outputPan);

        JPanel topWrapper = new JPanel(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 10));
        btnBack = new JButton("QUAY LẠI");
        btnSaveResult = new JButton("LƯU KẾT QUẢ");
        panel.add(btnBack);
        panel.add(btnSaveResult);
        topWrapper.add(panel, BorderLayout.WEST);
        topWrapper.add(centerPanel, BorderLayout.CENTER);
        this.add(topWrapper, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        txtKeyA = new JTextField(15);
        txtKeyB = new JTextField(15);
        txtKeyC = new JTextField(15);
        txtKeyD = new JTextField(15);
        btnGenKey = new JButton("TẠO KEY");
        btnEncrypt = new JButton("MÃ HOÁ VĂN BẢN");
        btnDecrypt = new JButton("GIẢI MÃ VĂN BẢN");
        btnClear = new JButton("XOÁ");
        btnSaveKey = new JButton("LƯU KEY");
        southPanel.add(txtKeyA);
        southPanel.add(txtKeyB);
        southPanel.add(txtKeyC);
        southPanel.add(txtKeyD);
        southPanel.add(btnGenKey);
        southPanel.add(btnEncrypt);
        southPanel.add(btnDecrypt);
        southPanel.add(btnClear);
        southPanel.add(btnSaveKey);
        this.add(southPanel, BorderLayout.SOUTH);
    }
    public JButton getSaveResult() {return btnSaveResult;}
    public JButton getSaveKey() {return btnSaveKey;}
    public JButton getBackButton() {
        return btnBack;
    }
    public JTextField getKeyFieldA() {
        return txtKeyA;
    }
    public JTextField getKeyFieldB() {
        return txtKeyB;
    }
    public JTextField getKeyFieldC() {
        return txtKeyC;
    }
    public JTextField getKeyFieldD() {
        return txtKeyD;
    }
    public JButton getGenKey(){
        return btnGenKey;
    }
    public JTextArea getInputArea() {
        return txtInput;
    }
    public JTextArea getOutputArea() {
        return txtOutput;
    }
    public JButton getEncryptButton() {
        return btnEncrypt;
    }
    public JButton getDecryptButton() {
        return btnDecrypt;
    }
    public JButton getClearButton() {
        return btnClear;
    }

}


