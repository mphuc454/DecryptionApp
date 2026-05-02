package MVC.View.TraditionalView;

import javax.swing.*;
import java.awt.*;

public class ViewCaesar extends JPanel {
    private JPanel contentPanel;
    private JButton btnBack;
    private JTextField txtKey;
    private JTextArea txtInput;
    private JTextArea txtOutput;
    private JButton btnEncrypt;
    private JButton btnDecrypt;
    private JButton btnClear;

    public ViewCaesar(){
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        btnBack = new JButton("QUAY LẠI");
        topPanel.add(btnBack, BorderLayout.WEST);

        txtKey = new JTextField();
        txtKey.setBorder(BorderFactory.createTitledBorder("NHẬP KEY"));
        topPanel.add(txtKey, BorderLayout.CENTER);

        JButton btnGenKey = new JButton("TẠO KEY");
        topPanel.add(btnGenKey, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        txtInput = new JTextArea(5, 20);
        txtInput.setBorder(BorderFactory.createTitledBorder("NHẬP VĂN BẢN"));

        txtOutput = new JTextArea(5, 20);
        txtOutput.setBorder(BorderFactory.createTitledBorder("KẾT QUẢ"));

        centerPanel.add(txtInput);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(txtOutput);

        this.add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnEncrypt = new JButton("MÃ HOÁ");
        btnDecrypt = new JButton("GIẢI MÃ");
        btnClear = new JButton("XOÁ");

        btnEncrypt.setMaximumSize(new Dimension(120, 40));
        btnDecrypt.setMaximumSize(new Dimension(120, 40));
        btnClear.setMaximumSize(new Dimension(120, 40));

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

    public JTextField getKeyField() {
        return txtKey;
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