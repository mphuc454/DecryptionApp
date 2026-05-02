package MVC.View;

import MVC.View.TraditionalView.ViewCaesar;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JPanel panelMain = new JPanel(cardLayout);
    JPanel panelMenu = new JPanel();
    JPanel panelTraditional = new JPanel();
    JPanel panelSymmetric = new JPanel();
    JPanel panelAsymmetric = new JPanel();
    JPanel panelHash = new JPanel();
   
    JPanel panelAffine = new JPanel();
    JPanel panelCaesar = new JPanel();

    final int maxWidth = Integer.MAX_VALUE;
    final int maxHeight = 300;

    public MyView(){
        setTitle("App Encryption Basic");
        setSize(850, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel menuContent = new JPanel();
       menuContent.setLayout(new BoxLayout(menuContent, BoxLayout.Y_AXIS));
       menuContent.add(panelTraditional);
       menuContent.add(Box.createVerticalStrut(10));
       menuContent.add(panelSymmetric);
       menuContent.add(Box.createVerticalStrut(10));
       menuContent.add(panelAsymmetric);
       menuContent.add(Box.createVerticalStrut(10));
       menuContent.add(panelHash);

       panelMenu.setLayout(new BorderLayout());
       panelMenu.add(menuContent, BorderLayout.NORTH);

       panelTraditional.setLayout(new BoxLayout(panelTraditional, BoxLayout.X_AXIS));
       panelSymmetric.setLayout(new BoxLayout(panelSymmetric, BoxLayout.X_AXIS));
       panelAsymmetric.setLayout(new BoxLayout(panelAsymmetric, BoxLayout.X_AXIS));
       panelHash.setLayout(new BoxLayout(panelHash, BoxLayout.X_AXIS));

       panelTraditional.setBorder(BorderFactory.createTitledBorder("Mã hoá truyền thống"));
       panelTraditional.setMaximumSize(new Dimension(maxWidth, maxHeight));
       JButton btnAffine = new JButton("Mã hoá Affine");
       JButton btnCaesar = new JButton("Mã hoá Caesar");
       JButton btnHill = new JButton("Mã hoá Hill");
       JButton btnHoanVi = new JButton("Mã hoá hoán vị");
       JButton btnDichChuyen = new JButton("Mã hoá dịch chuyển");
       JButton btnVigenere = new JButton("Mã hoá Vigenere");

       panelTraditional.add(btnAffine);
       panelTraditional.add(Box.createHorizontalStrut(10));
       panelTraditional.add(btnCaesar);
       panelTraditional.add(Box.createHorizontalStrut(10));
       panelTraditional.add(btnHill);
       panelTraditional.add(Box.createHorizontalStrut(10));
       panelTraditional.add(btnHoanVi);
       panelTraditional.add(Box.createHorizontalStrut(10));
       panelTraditional.add(btnDichChuyen);
       panelTraditional.add(Box.createHorizontalStrut(10));
       panelTraditional.add(btnVigenere);
       panelTraditional.add(Box.createHorizontalGlue());

        panelSymmetric.setBorder(BorderFactory.createTitledBorder("Mã hoá Đối xứng"));
        panelSymmetric.setMaximumSize(new Dimension(maxWidth, maxHeight));
        JButton btnSymmetric = new JButton("Asymmetric");
        panelSymmetric.add(btnSymmetric);
        panelSymmetric.add(Box.createHorizontalGlue());

        panelAsymmetric.setBorder(BorderFactory.createTitledBorder("Mã hoá Bất Đối xứng"));
        panelAsymmetric.setMaximumSize(new Dimension(maxWidth, maxHeight));
        JButton btnAsymmetric = new JButton("Symmetric");
        panelAsymmetric.add(btnAsymmetric);
        panelAsymmetric.add(Box.createHorizontalGlue());

        panelHash.setBorder(BorderFactory.createTitledBorder("Hàm băm"));
        panelHash.setMaximumSize(new Dimension(maxWidth, maxHeight));
        JButton btnHash = new JButton("Hash");
        panelHash.add(btnHash);
        panelHash.add(Box.createHorizontalGlue());

        ViewCaesar viewCaesar = new ViewCaesar();
        panelCaesar = viewCaesar;
        viewCaesar.getBackButton().addActionListener(e -> cardLayout.show(panelMain, "menu"));

        panelMain.add(panelMenu, "menu");
        panelMain.add(panelAffine, "affine");
        panelMain.add(panelCaesar, "caesar");

        btnAffine.addActionListener(e -> cardLayout.show(panelMain, "affine"));
        btnCaesar.addActionListener(e -> cardLayout.show(panelMain, "caesar"));
        btnSymmetric.addActionListener(e -> cardLayout.show(panelMain, "symmetric"));
        btnAsymmetric.addActionListener(e -> cardLayout.show(panelMain, "asymmetric"));
        btnHash.addActionListener(e -> cardLayout.show(panelMain, "hash"));

        this.add(panelMain, BorderLayout.CENTER);

    }



}
