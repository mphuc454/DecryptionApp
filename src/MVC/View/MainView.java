package MVC.View;

import MVC.Controller.OtherController.AsymmetricController;
import MVC.Controller.OtherController.SymmetricController;
import MVC.Controller.TraditionalController.*;
import MVC.Model.OtherModel.AsymmetricCipher;
import MVC.Model.OtherModel.HashCipher;
import MVC.Model.OtherModel.SymmetricCipher;
import MVC.Model.TraditionalModel.*;
import MVC.View.OtherView.ViewHash;
import MVC.View.OtherView.ViewAsymmetric;
import MVC.View.OtherView.ViewSymmetric;
import MVC.View.TraditionalView.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JPanel panel = new JPanel(cardLayout);
    JPanel panelMenu = new JPanel();
    JPanel panelTruyenThong = new JPanel();
    JPanel panelDoiXung = new JPanel();
    JPanel panelBatDoiXung = new JPanel();
    JPanel panelBam = new JPanel();
    JPanel menuContent = new JPanel();
    JPanel panelCaesar;
    JPanel panelSubstitution;
    JPanel panelAffine;
    JPanel panelVigenere;
    JPanel panelTransposition;
    JPanel panelHill;
    JPanel panelSymmetric;
    JPanel panelAsymmetric;
    JPanel panelHash;
    ViewCaesar viewCaesar = new ViewCaesar();
    CaesarCipher caesarCipher = new CaesarCipher();
    ViewSubstitution viewSubstitution = new ViewSubstitution();
    SubstitutionCipher substitutionCipher = new SubstitutionCipher();
    ViewAffine viewAffine = new ViewAffine();
    AffineCipher affineCipher = new AffineCipher();
    ViewVigenere viewVigenere = new ViewVigenere();
    VigenereCipher vigenereCipher = new VigenereCipher();
    ViewTransposition viewTransposition = new ViewTransposition();
    TranspositionCipher transpositionCipher = new TranspositionCipher();
    ViewHill viewHill = new ViewHill();
    HillCipher hillCipher = new HillCipher();
    ViewSymmetric viewSymmetric = new ViewSymmetric();
    SymmetricCipher symmetricCipher = new SymmetricCipher();
    ViewAsymmetric viewAsymmetric = new ViewAsymmetric();
    AsymmetricCipher asymmetricCipher = new AsymmetricCipher();
    ViewHash hashView = new ViewHash();
    HashCipher hashCipher = new HashCipher();

    public MainView(){
        this.setTitle("App Encryption Basic");
        this.setSize(850, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuContent.setLayout(new BoxLayout(menuContent, BoxLayout.Y_AXIS));
        panelMenu.setLayout(new BorderLayout());

        panelTruyenThong.setLayout(new BoxLayout(panelTruyenThong, BoxLayout.X_AXIS));
        panelTruyenThong.setBorder(BorderFactory.createTitledBorder("Mã hoá truyền thống"));
        panelTruyenThong.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        JButton btnSubstitution = new JButton("Mã hoá thay thế");
        JButton btnCaesar = new JButton("Mã hoá Caesar");
        JButton btnAffine = new JButton("Mã hoá Affine");
        JButton btnVigenere = new JButton("Mã hoá Vigenere");
        JButton btnHill = new JButton("Mã hoá Hill");
        JButton btnTransposition = new JButton("Mã hoá hoán vị");

        panelTruyenThong.add(btnSubstitution);
        panelTruyenThong.add(btnCaesar);
        panelTruyenThong.add(btnAffine);
        panelTruyenThong.add(btnVigenere);
        panelTruyenThong.add(btnHill);
        panelTruyenThong.add(btnTransposition);
        menuContent.add(panelTruyenThong);

        panelDoiXung.setLayout(new BoxLayout(panelDoiXung, BoxLayout.X_AXIS));
        panelDoiXung.setBorder(BorderFactory.createTitledBorder("Mã hoá Đối xứng"));
        panelDoiXung.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        JButton btnSymmetric = new JButton("Symmetric");
        panelDoiXung.add(btnSymmetric);
        menuContent.add(panelDoiXung);

        panelBatDoiXung.setLayout(new BoxLayout(panelBatDoiXung, BoxLayout.X_AXIS));
        panelBatDoiXung.setBorder(BorderFactory.createTitledBorder("Mã hoá Bất Đối xứng"));
        panelBatDoiXung.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        JButton btnAsymmetric = new JButton("Asymmetric");
        panelBatDoiXung.add(btnAsymmetric);
        menuContent.add(panelBatDoiXung);

        panelBam.setLayout(new BoxLayout(panelBam, BoxLayout.X_AXIS));
        panelBam.setBorder(BorderFactory.createTitledBorder("Hàm băm"));
        panelBam.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        JButton btnHash = new JButton("Hash");
        panelBam.add(btnHash);
        menuContent.add(panelBam);

        panelMenu.add(menuContent, BorderLayout.NORTH);
        panel.add(panelMenu, "menu");

        panelCaesar = viewCaesar;
        panel.add(panelCaesar, "caesar");
        new CaesarController(caesarCipher, viewCaesar, cardLayout, panel);

        panelSubstitution = viewSubstitution;
        panel.add(panelSubstitution, "substitution");
        new SubstitutionController(substitutionCipher, viewSubstitution, cardLayout, panel);

        panelAffine = viewAffine;
        panel.add(panelAffine, "affine");
        new AffineController(affineCipher, viewAffine, cardLayout, panel);

        panelVigenere = viewVigenere;
        panel.add(panelVigenere, "vigenere");
        new VigenereController(vigenereCipher, viewVigenere, cardLayout, panel);

        panelTransposition = viewTransposition;
        panel.add(panelTransposition, "transposition");
        new TranspositionController(transpositionCipher, viewTransposition, cardLayout, panel);

        panelHill = viewHill;
        panel.add( panelHill, "hill");
        new HillController(hillCipher, viewHill, cardLayout, panel);

        panelSymmetric = viewSymmetric;
        panel.add(panelSymmetric, "symmetric");
        new SymmetricController(symmetricCipher, viewSymmetric, cardLayout, panel);

        panelAsymmetric = viewAsymmetric;
        panel.add(panelAsymmetric, "asymmetric");
        new AsymmetricController(asymmetricCipher, viewAsymmetric, cardLayout, panel);

        panelHash = hashView;
        panel.add(panelHash, "hash");

        btnSubstitution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "substitution");
            }
        });
        btnCaesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "caesar");
            }
        });
        btnAffine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"affine");
            }
        });
        btnVigenere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"vigenere");
            }
        });
        btnTransposition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"transposition");
            }
        });
        btnHill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"hill");
            }
        });
        btnSymmetric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"symmetric");
            }
        });
        btnAsymmetric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"asymmetric");
            }
        });
        btnHash.addActionListener(new ActionListener() { @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"hash");
            }
        });

        this.add(panel, BorderLayout.CENTER);

    }
}
