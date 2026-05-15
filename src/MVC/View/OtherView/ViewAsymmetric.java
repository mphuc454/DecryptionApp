package MVC.View.OtherView;

import javax.swing.*;
import java.awt.*;

public class ViewAsymmetric extends JPanel {
    private JButton btnBack;
    private JComboBox<String>algorithmASymmetric;
    private JComboBox<String>modeASymmetric;
    private JComboBox<String>paddingASymmetric;
    private JComboBox<String>keySizeASymmetric;
    private JTextField inputPrivateKey;
    private JTextField inputPublicKey;
    private JTextArea txtInput;
    private JTextArea txtOutput;
    private JButton btnEncrypt;
    private JButton btnDecrypt;
    private JButton btnEncryptFile;
    private JButton btnDecryptFile;
    private JButton btnClear;
    private JButton btnGenKey;
    private JButton btnPublicKey;
    private JButton btnPrivateKey;
    private JButton importPublicKey;
    private JButton importPrivateKey;


    public ViewAsymmetric(){
        this.setLayout(new BorderLayout(10,10));

        btnBack = new JButton("QUAY LẠI");
        JPanel panel = new JPanel();
        panel.add(btnBack);
        this.add(panel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        JPanel optionPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel lb1 = new JLabel("Thuật toán Bất đối xứng");
        optionPanel.add(lb1);
        algorithmASymmetric = new JComboBox<>(new String[]{"RSA"});
        optionPanel.add(algorithmASymmetric);
        JLabel lb2 = new JLabel("Mode");
        optionPanel.add(lb2);
        modeASymmetric = new JComboBox<>(new String[]{"ECB"});
        optionPanel.add(modeASymmetric);
        JLabel lb3 = new JLabel("Padding");
        optionPanel.add(lb3);
        paddingASymmetric = new JComboBox<>(new String[]{"PKCS1Padding", "OAEPWithSHA-1AndMGF1Padding", "OAEPWithSHA-256AndMGF1Padding"});
        optionPanel.add(paddingASymmetric);
        JLabel lb4 = new JLabel("Key size");
        optionPanel.add(lb4);
        keySizeASymmetric = new JComboBox<>(new String[]{"1024", "2048"});
        optionPanel.add(keySizeASymmetric);
        JLabel lb5 = new JLabel("Nhập khoá công khai: ");
        optionPanel.add(lb5);
        inputPublicKey = new JTextField(60);
        optionPanel.add(inputPublicKey);
        JLabel lb6 = new JLabel("Nhập khoá riêng tư: ");
        optionPanel.add(lb6);
        inputPrivateKey = new JTextField(60);
        optionPanel.add(inputPrivateKey);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(optionPanel, BorderLayout.CENTER);
        JPanel keyBtnPanel = new JPanel(new BorderLayout(10, 10));
        JPanel topBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnGenKey = new JButton("TẠO KEY");
        topBtnPanel.add(btnGenKey);
        keyBtnPanel.add(topBtnPanel, BorderLayout.NORTH);
        JPanel gridBtnPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        btnPublicKey = new JButton("LƯU PUBLIC KEY");
        btnPrivateKey = new JButton("LƯU PRIVATE KEY");
        importPublicKey = new JButton("IMPORT PUBLIC KEY");
        importPrivateKey = new JButton("IMPORT PRIVATE KEY");
        gridBtnPanel.add(btnPublicKey);
        gridBtnPanel.add(btnPrivateKey);
        gridBtnPanel.add(importPublicKey);
        gridBtnPanel.add(importPrivateKey);
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
        btnEncrypt = new JButton("MÃ HOÁ VĂN BẢN");
        btnDecrypt = new JButton("GIẢI MÃ VĂN BẢN");
        btnEncryptFile = new JButton("MÃ HOÁ FILE");
        btnDecryptFile = new JButton("GIẢI MÃ FILE");
        southPanel.add(btnClear);
        southPanel.add(btnEncrypt);
        southPanel.add(btnDecrypt);
        southPanel.add(btnEncryptFile);
        southPanel.add(btnDecryptFile);
        centerPanel.add(southPanel, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);

    }
    public JTextField getInputPrivateKey() {return inputPrivateKey;}
    public JTextField getInputPublicKey() {return inputPublicKey;}
    public JButton getBackButton() {return btnBack;}
    public JButton getGenKey(){
        return btnGenKey;
    }
    public JTextArea getInputArea() {return txtInput;}
    public JTextArea getOutputArea() {return txtOutput;}
    public JButton getEncryptButton() {return btnEncrypt;}
    public JButton getDecryptButton() {return btnDecrypt;}
    public JButton getClearButton() {return btnClear;}
    public JComboBox<String> getAlgorithmASymmetric() {return algorithmASymmetric;}
    public JComboBox<String> getModeASymmetric() {return modeASymmetric;}
    public JComboBox<String> getPaddingASymmetric() {return paddingASymmetric;}
    public JComboBox<String> getKeySizeASymmetric() {return keySizeASymmetric;}
    public JButton getEncryptFileButton() {return btnEncryptFile;}
    public JButton getDecryptFileButton() {return btnDecryptFile;}
    public JButton getSavePK() {return btnPublicKey;}
    public JButton getSavePrivateK() {return btnPrivateKey;}
    public JButton getImportPublicKey() {return importPublicKey;}
    public JButton getImportPrivateKey() {return importPrivateKey;}
}
