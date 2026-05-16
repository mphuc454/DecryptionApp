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
    private JButton importKey;
    private JButton btnSaveResult;

    public ViewSymmetric(){
        this.setLayout(new BorderLayout(10,10));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        btnBack = new JButton("QUAY LẠI");
        btnSaveResult = new JButton("LƯU KẾT QUẢ");
        btnClear = new JButton("XOÁ");
        panel.add(btnBack);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnSaveResult);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnClear);
        this.add(panel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        JPanel optionPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel lb1 = new JLabel("Thuật toán đối xứng");
        optionPanel.add(lb1);
        algorithmSymmetric = new JComboBox<>(new String[]{"AES","DES","Camellia","Serpent","RC6","Twofish"});
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
        JLabel lb5 = new JLabel("Nhập khoá: ");
        optionPanel.add(lb5);
        inputKey = new JTextField(60);
        optionPanel.add(inputKey);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(optionPanel, BorderLayout.CENTER);
        JPanel keyBtnPanel = new JPanel(new BorderLayout(10, 10));
        JPanel topBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnGenKey = new JButton("TẠO KEY");
        topBtnPanel.add(btnGenKey);
        keyBtnPanel.add(topBtnPanel, BorderLayout.NORTH);
        JPanel gridBtnPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        btnsaveKey = new JButton("LƯU KEY");
        importKey = new JButton("IMPORT KEY");
        gridBtnPanel.add(btnsaveKey);
        gridBtnPanel.add(importKey);
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
        btnEncrypt = new JButton("MÃ HOÁ VĂN BẢN");
        btnDecrypt = new JButton("GIẢI MÃ VĂN BẢN");
        btnEncryptFile = new JButton("MÃ HOÁ FILE");
        btnDecryptFile = new JButton("GIẢI MÃ FILE");
        southPanel.add(btnEncrypt);
        southPanel.add(btnDecrypt);
        southPanel.add(btnEncryptFile);
        southPanel.add(btnDecryptFile);
        centerPanel.add(southPanel, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);
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
                    modeSymmetric.addItem("CTR");
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
    public JButton getSaveResult() {return btnSaveResult;}
    public JButton getImportKey() {return importKey;}
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
