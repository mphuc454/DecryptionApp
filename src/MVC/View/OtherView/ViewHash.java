package MVC.View.OtherView;

import javax.swing.*;
import java.awt.*;


public class ViewHash extends JPanel {
    private JButton btnBack;
    private JComboBox<String>algorithmHash;
    private JTextArea txtInput;
    private JTextArea txtOutput;
    private JButton btnEncrypt;
    private JButton btnEncryptFile;
    private JButton btnClear;

    public ViewHash(){
        this.setLayout(new BorderLayout(10,10));
        btnBack = new JButton("QUAY LẠI");
        JPanel panel = new JPanel();
        panel.add(btnBack);
        this.add(panel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        JPanel optionPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel lb1 = new JLabel("Thuật toán đối xứng");
        optionPanel.add(lb1);
        algorithmHash = new JComboBox<>(new String[]{"MD5","SHA-1","SHA-224","SHA-256","SHA-384","SHA-512","SHA-512/224","SHA-512/256","MD2"});
        optionPanel.add(algorithmHash);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(optionPanel, BorderLayout.CENTER);
        JPanel keyBtnPanel = new JPanel(new BorderLayout(10, 10));
        JPanel topBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        keyBtnPanel.add(topBtnPanel, BorderLayout.NORTH);
        JPanel gridBtnPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        keyBtnPanel.add(gridBtnPanel, BorderLayout.CENTER);
        topPanel.add(keyBtnPanel, BorderLayout.EAST);
        centerPanel.add(topPanel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 10, 10));
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
        textPanel.add(inputPan);
        textPanel.add(outputPan);
        centerPanel.add(textPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnClear = new JButton("XOÁ");
        btnEncrypt = new JButton("BĂM VĂN BẢN");
        btnEncryptFile = new JButton("BĂM FILE");
        southPanel.add(btnClear);
        southPanel.add(btnEncrypt);
        southPanel.add(btnEncryptFile);
        centerPanel.add(southPanel, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);

//        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
//        btnBack = new JButton("QUAY LẠI");
//        topPanel.add(btnBack, BorderLayout.WEST);
//        JPanel optionPanel = new JPanel(new GridLayout(5,2,10,10));
//        JLabel lb1 = new JLabel("Thuật toán Hàm băm");
//        optionPanel.add(lb1);
//        algorithmHash = new JComboBox<>(new String[]{"MD5","SHA-1","SHA-224","SHA-256","SHA-384","SHA-512","SHA-512/224","SHA-512/256"});
//        optionPanel.add(algorithmHash);
//        btnEncrypt = new JButton("Mã Hoá Văn bản");
//        btnEncryptFile = new JButton("Mã Hoá file");
//        btnClear = new JButton("Xoá");
//        optionPanel.add(btnEncrypt);
//        optionPanel.add(btnEncryptFile);
//        optionPanel.add(btnClear);
//        topPanel.add(optionPanel, BorderLayout.CENTER);
//        this.add(topPanel, BorderLayout.NORTH);
//
//        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
//        JPanel inputPan = new JPanel(new BorderLayout());
//        JLabel titleInput = new JLabel("Nhập văn bản");
//        txtInput = new JTextArea(5, 20);
//        inputPan.add(titleInput, BorderLayout.NORTH);
//        inputPan.add(new JScrollPane(txtInput) , BorderLayout.CENTER);
//        JPanel outputPan = new JPanel(new BorderLayout());
//        JLabel titleOutput = new JLabel("Kết quả");
//        txtOutput = new JTextArea(5, 20);
//        txtOutput.setEditable(false);
//        outputPan.add(titleOutput , BorderLayout.NORTH);
//        outputPan.add(new JScrollPane(txtOutput), BorderLayout.CENTER);
//        centerPanel.add(inputPan);
//        centerPanel.add(outputPan);
//        this.add(centerPanel, BorderLayout.CENTER);
    }
    public JButton getBackButton() {return btnBack;}
    public JTextArea getInputArea() {return txtInput;}
    public JTextArea getOutputArea() {return txtOutput;}
    public JButton getEncryptButton() {return btnEncrypt;}
    public JButton getClearButton() {return btnClear;}
    public JButton getEncryptFileButton() {return btnEncryptFile;}
    public JComboBox<String> getAlgorithmHash() {return algorithmHash;}
}
