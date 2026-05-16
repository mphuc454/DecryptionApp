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
    private JButton btnSaveResult;

    public ViewHash(){
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
        btnEncrypt = new JButton("BĂM VĂN BẢN");
        btnEncryptFile = new JButton("BĂM FILE");
        southPanel.add(btnEncrypt);
        southPanel.add(btnEncryptFile);
        centerPanel.add(southPanel, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);
    }
    public JButton getSaveResult() {return btnSaveResult;}
    public JButton getBackButton() {return btnBack;}
    public JTextArea getInputArea() {return txtInput;}
    public JTextArea getOutputArea() {return txtOutput;}
    public JButton getEncryptButton() {return btnEncrypt;}
    public JButton getClearButton() {return btnClear;}
    public JButton getEncryptFileButton() {return btnEncryptFile;}
    public JComboBox<String> getAlgorithmHash() {return algorithmHash;}
}
