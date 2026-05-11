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

    public ViewAsymmetric(){
        this.setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        btnBack = new JButton("QUAY LẠI");
        topPanel.add(btnBack, BorderLayout.WEST);
        JPanel optionPanel = new JPanel(new GridLayout(6,2,10,10));
        JLabel lb1 = new JLabel("Thuật toán đối xứng");
        optionPanel.add(lb1);
        algorithmASymmetric = new JComboBox<>(new String[]{"RSA"});
        optionPanel.add(algorithmASymmetric);
        JLabel lb2 = new JLabel("Mode");
        optionPanel.add(lb2);
        modeASymmetric = new JComboBox<>(new String[] {"ECB"});
        optionPanel.add(modeASymmetric);
        JLabel lb3 = new JLabel("Padding");
        optionPanel.add(lb3);
        paddingASymmetric = new JComboBox<>(new String[]{"PKCS1Padding","OAEPWithSHA-1AndMGF1Padding","OAEPWithSHA-256AndMGF1Padding"});
        optionPanel.add(paddingASymmetric);
        JLabel lb4 = new JLabel("Key size");
        optionPanel.add(lb4);
        keySizeASymmetric = new JComboBox<>(new String[]{"1024","2048"});
        optionPanel.add(keySizeASymmetric);
        JLabel lb5 = new JLabel("Nhập khoá công khai: ");
        optionPanel.add(lb5);
        inputPublicKey = new JTextField(60);
        optionPanel.add(inputPublicKey);
        JLabel lb6 = new JLabel("Nhập khoá riêng tư: ");
        optionPanel.add(lb6);
        inputPrivateKey = new JTextField(60);
        optionPanel.add(inputPrivateKey);
        topPanel.add(optionPanel, BorderLayout.CENTER);
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.Y_AXIS));
        btnGenKey= new JButton("Tạo key");
        btnPublicKey = new JButton("Lưu Public Key");
        btnPrivateKey = new JButton("Lưu Private Key");
        keyPanel.add(btnGenKey);
        keyPanel.add(Box.createVerticalStrut(10));
        keyPanel.add(btnPublicKey);
        keyPanel.add(Box.createVerticalStrut(10));
        keyPanel.add(btnPrivateKey);
        topPanel.add(keyPanel, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel inputPan = new JPanel(new BorderLayout());
        JLabel titleInput = new JLabel("Nhập văn bản");
        txtInput = new JTextArea(5, 20);
        inputPan.add(titleInput, BorderLayout.NORTH);
        inputPan.add(new JScrollPane(txtInput) , BorderLayout.CENTER);
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
        btnEncrypt = new JButton("Mã Hoá");
        btnDecrypt = new JButton("Giải mã");
        btnEncryptFile = new JButton("Mã Hoá file");
        btnDecryptFile = new JButton("Giải mã file");
        btnClear = new JButton("Xoá");
        rightPanel.add(btnEncrypt);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnDecrypt);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnEncryptFile);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnDecryptFile);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnClear);
        this.add(rightPanel, BorderLayout.EAST);
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
}
