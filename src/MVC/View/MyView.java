package MVC.View;

import MVC.Controller.TraditionalController.CaesarController;
import MVC.Controller.TraditionalController.SubstitutionController;
import MVC.Model.TraditionalModel.CaesarCipher;
import MVC.Model.TraditionalModel.SubstitutionCipher;
import MVC.View.TraditionalView.ViewCaesar;
import MVC.View.TraditionalView.ViewSubstitution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyView extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JPanel panelMain = new JPanel(cardLayout);
    JPanel panelMenu = new JPanel();
    JPanel panelTraditional = new JPanel();
    JPanel panelSymmetric = new JPanel();
    JPanel panelAsymmetric = new JPanel();
    JPanel panelHash = new JPanel();
    JPanel panelCaesar;
    JPanel panelSubstitution;

    ViewCaesar viewCaesar = new ViewCaesar();
    CaesarCipher caesarCipher = new CaesarCipher();

    ViewSubstitution viewSubstitution = new ViewSubstitution();
    SubstitutionCipher substitutionCipher = new SubstitutionCipher();


    public MyView(){
        this.setTitle("App Encryption Basic");
        this.setSize(850, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel menuContent = new JPanel();
       menuContent.setLayout(new BoxLayout(menuContent, BoxLayout.Y_AXIS));
       panelMenu.setLayout(new BorderLayout());

       panelTraditional.setLayout(new BoxLayout(panelTraditional, BoxLayout.X_AXIS));
       panelTraditional.setBorder(BorderFactory.createTitledBorder("Mã hoá truyền thống"));
       panelTraditional.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
       JButton btnAffine = new JButton("Mã hoá Affine");
       panelTraditional.add(btnAffine);
       JButton btnCaesar = new JButton("Mã hoá Caesar");
       panelTraditional.add(btnCaesar);
       JButton btnHill = new JButton("Mã hoá Hill");
       panelTraditional.add(btnHill);
       JButton btnHoanVi = new JButton("Mã hoá hoán vị");
       panelTraditional.add(btnHoanVi);
       JButton btnDichChuyen = new JButton("Mã hoá dịch chuyển");
       panelTraditional.add(btnDichChuyen);
       JButton btnVigenere = new JButton("Mã hoá Vigenere");
       panelTraditional.add(btnVigenere);
       menuContent.add(panelTraditional);

       panelSymmetric.setLayout(new BoxLayout(panelSymmetric, BoxLayout.X_AXIS));
       panelSymmetric.setBorder(BorderFactory.createTitledBorder("Mã hoá Đối xứng"));
       panelSymmetric.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
       JButton btnSymmetric = new JButton("Asymmetric");
       panelSymmetric.add(btnSymmetric);
       menuContent.add(panelSymmetric);

       panelAsymmetric.setLayout(new BoxLayout(panelAsymmetric, BoxLayout.X_AXIS));
       panelAsymmetric.setBorder(BorderFactory.createTitledBorder("Mã hoá Bất Đối xứng"));
       panelAsymmetric.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
       JButton btnAsymmetric = new JButton("Symmetric");
       panelAsymmetric.add(btnAsymmetric);
       menuContent.add(panelAsymmetric);

       panelHash.setLayout(new BoxLayout(panelHash, BoxLayout.X_AXIS));
       panelHash.setBorder(BorderFactory.createTitledBorder("Hàm băm"));
       panelHash.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
       JButton btnHash = new JButton("Hash");
       panelHash.add(btnHash);
       menuContent.add(panelHash);

       panelMenu.add(menuContent, BorderLayout.NORTH);
       panelMain.add(panelMenu, "menu");

       panelCaesar = viewCaesar;
       panelMain.add(panelCaesar, "caesar");
       new CaesarController(caesarCipher, viewCaesar, cardLayout, panelMain);


       panelSubstitution = viewSubstitution;
       panelMain.add(panelSubstitution, "substitution");
       new SubstitutionController(substitutionCipher, viewSubstitution, cardLayout, panelMain);



        btnDichChuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "substitution");
            }
        });
        btnCaesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "caesar");
            }
        });

        this.add(panelMain, BorderLayout.CENTER);

    }
}
