package MVC.View.TraditionalView;

import javax.swing.*;
import java.awt.*;

public class ViewAffine extends JPanel {
    private JButton btnBack;
    private JTextField txtKeyA;
    private JTextField txtKeyB;
    private JTextArea txtInput;
    private JTextArea txtOutput;
    private JButton btnEncrypt;
    private JButton btnDecrypt;
    private JButton btnClear;
    private JButton btnGenKey;

    public ViewAffine(){
        this.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        btnBack = new JButton("QUAY LẠI");
        topPanel.add(btnBack, BorderLayout.WEST);
        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lbA = new JLabel("a");
        keyPanel.add(lbA);
        txtKeyA = new JTextField(30);
        keyPanel.add(txtKeyA);
        JLabel lbB = new JLabel("b");
        keyPanel.add(lbB);
        txtKeyB = new JTextField(30);
        keyPanel.add(txtKeyB);
        topPanel.add(keyPanel, BorderLayout.CENTER);
        btnGenKey = new JButton("TẠO KEY");
        topPanel.add(btnGenKey, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel inputPan = new JPanel(new BorderLayout());
        JLabel titleInput = new JLabel("Nhập văn bản");
        txtInput = new JTextArea(5, 20);
        inputPan.add(titleInput, BorderLayout.NORTH);
        inputPan.add(new JScrollPane(txtInput), BorderLayout.CENTER);
        JPanel outputPan = new JPanel(new BorderLayout());
        JLabel titleOutput = new JLabel("Kết quả");
        txtOutput = new JTextArea(5, 20);
        txtOutput.setEditable(false);
        outputPan.add(titleOutput , BorderLayout.NORTH);
        outputPan.add(new JScrollPane(txtOutput), BorderLayout.CENTER);
        centerPanel.add(inputPan);
        centerPanel.add(outputPan);
        this.add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        btnEncrypt = new JButton("Mã hoá");
        btnDecrypt = new JButton("Giải Mã");
        btnClear = new JButton("Xoá");
        rightPanel.add(btnEncrypt);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnDecrypt);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnClear);
        this.add(rightPanel, BorderLayout.EAST);
    }
    public JButton getBackButton() {
        return btnBack;
    }
    public JTextField getKeyFieldA() {
        return txtKeyA;
    }
    public JTextField getKeyFieldB() {
        return txtKeyB;
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
