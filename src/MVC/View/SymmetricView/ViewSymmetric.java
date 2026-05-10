package MVC.View.SymmetricView;

import javax.swing.*;
import java.awt.*;

public class ViewSymmetric extends JPanel {
    private JButton btnBack;
    private JComboBox<String>algorithmSymmetric;
    private JComboBox<String>modeSymmetric;
    private JComboBox<String>paddingSymmetric;
    private JComboBox<String>keySizeSymmetric;
    private JTextArea txtInput;
    private JTextArea txtOutput;
    private JButton btnEncrypt;
    private JButton btnDecrypt;
    private JButton btnEncryptFile;
    private JButton btnDecryptFile;
    private JButton btnClear;
    private JButton btnGenKey;
    private JButton btnsaveKey;

    public ViewSymmetric(){
        this.setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        btnBack = new JButton("QUAY LẠI");
        topPanel.add(btnBack, BorderLayout.WEST);
        JPanel optionPanel = new JPanel(new GridLayout(4,2,10,10));
        JLabel lb1 = new JLabel("Thuật toán đối xứng");
        optionPanel.add(lb1);
        algorithmSymmetric = new JComboBox<>(new String[]{"AES","DES"});
        optionPanel.add(algorithmSymmetric);
        JLabel lb2 = new JLabel("Mode");
        optionPanel.add(lb2);
        modeSymmetric = new JComboBox<>(new String[]{"CBC","ECB"});
        optionPanel.add(modeSymmetric);
        JLabel lb3 = new JLabel("Padding");
        optionPanel.add(lb3);
        paddingSymmetric = new JComboBox<>(new String[]{"PKCS5Padding","NoPadding"});
        optionPanel.add(paddingSymmetric);
        JLabel lb4 = new JLabel("Key size");
        optionPanel.add(lb4);
        keySizeSymmetric = new JComboBox<>(new String[]{"56","128","168","256"});
        optionPanel.add(keySizeSymmetric);
        topPanel.add(optionPanel, BorderLayout.CENTER);
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.Y_AXIS));
        btnGenKey= new JButton("Tạo key");
        btnsaveKey = new JButton("Lưu key");
        keyPanel.add(btnGenKey);
        keyPanel.add(Box.createVerticalStrut(10));
        keyPanel.add(btnsaveKey);
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

}
