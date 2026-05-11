package MVC.View.OtherView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewSymmetric extends JPanel {
    private JButton btnBack;
    private JComboBox<String>algorithmSymmetric;
    private JComboBox<String>modeSymmetric;
    private JComboBox<String>paddingSymmetric;
    private JComboBox<String>keySizeSymmetric;
    private JTextField inputKey;
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
        JPanel optionPanel = new JPanel(new GridLayout(5,2,10,10));
        JLabel lb1 = new JLabel("Thuật toán đối xứng");
        optionPanel.add(lb1);
        algorithmSymmetric = new JComboBox<>(new String[]{"AES","DES","Camellia","Serpent","RC6","Twofish","CAST5"});
        optionPanel.add(algorithmSymmetric);
        JLabel lb2 = new JLabel("Mode");
        optionPanel.add(lb2);
        modeSymmetric = new JComboBox<>(new String[] {"CBC","ECB","CFB","OFB","CTR"});
        optionPanel.add(modeSymmetric);
        JLabel lb3 = new JLabel("Padding");
        optionPanel.add(lb3);
        paddingSymmetric = new JComboBox<>(new String[]{"PKCS5Padding","NoPadding"});
        optionPanel.add(paddingSymmetric);
        JLabel lb4 = new JLabel("Key size");
        optionPanel.add(lb4);
        keySizeSymmetric = new JComboBox<>(new String[] {"128","192","256"});
        optionPanel.add(keySizeSymmetric);
        JLabel lb = new JLabel("Nhập khoá: ");
        optionPanel.add(lb);
        inputKey = new JTextField(60);
        optionPanel.add(inputKey);
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

        algorithmSymmetric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameAlgorithm = algorithmSymmetric.getSelectedItem().toString();
                modeSymmetric.removeAllItems();
                keySizeSymmetric.removeAllItems();
                if (nameAlgorithm.equals("DES")) {
                    keySizeSymmetric.addItem("56");
                    modeSymmetric.addItem("CBC");
                    modeSymmetric.addItem("ECB");
                    modeSymmetric.addItem("CFB");
                    modeSymmetric.addItem("OFB");
                }else if(nameAlgorithm.equals("CAST5")){
                    keySizeSymmetric.addItem("128");
                    modeSymmetric.addItem("ECB");
                }else{
                    keySizeSymmetric.addItem("128");
                    keySizeSymmetric.addItem("192");
                    keySizeSymmetric.addItem("256");
                    modeSymmetric.addItem("CBC");
                    modeSymmetric.addItem("ECB");
                    modeSymmetric.addItem("CFB");
                    modeSymmetric.addItem("OFB");
                    modeSymmetric.addItem("CTR");
                }
            }
        });
    }
    public JTextField getKeyField() {return inputKey;}
    public JButton getBackButton() {return btnBack;}
    public JButton getGenKey(){return btnGenKey;}
    public JTextArea getInputArea() {return txtInput;}
    public JTextArea getOutputArea() {return txtOutput;}
    public JButton getEncryptButton() {return btnEncrypt;}
    public JButton getDecryptButton() {return btnDecrypt;}
    public JButton getClearButton() {return btnClear;}
    public JComboBox<String> getAlgorithmSymmetric() {return algorithmSymmetric;}
    public JComboBox<String> getModeSymmetric() {return modeSymmetric;}
    public JComboBox<String> getPaddingSymmetric() {return paddingSymmetric;}
    public JComboBox<String> getKeySizeSymmetric() {return keySizeSymmetric;}
    public JButton getEncryptFileButton() {return btnEncryptFile;}
    public JButton getDecryptFileButton() {return btnDecryptFile;}
    public JButton getSaveKeyButton() {return btnsaveKey;}


}
