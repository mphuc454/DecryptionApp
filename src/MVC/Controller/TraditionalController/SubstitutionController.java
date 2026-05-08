package MVC.Controller.TraditionalController;

import MVC.Model.TraditionalModel.SubstitutionCipher;
import MVC.View.TraditionalView.ViewSubstitution;

import javax.swing.*;
import java.awt.*;

public class SubstitutionController {
    private SubstitutionCipher substitutionCipher;
    private ViewSubstitution viewSubstitution;
    private CardLayout cardLayout;
    private JPanel panel;

    public SubstitutionController(SubstitutionCipher substitutionCipher, ViewSubstitution viewSubstitution, CardLayout cardLayout, JPanel panel) {
        this.substitutionCipher = substitutionCipher;
        this.viewSubstitution = viewSubstitution;
        this.cardLayout = cardLayout;
        this.panel = panel;
    }
}
