package org.xmolecules.ide.intellij.settings;

import com.intellij.util.ui.FormBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SettingsView {

    private final JLabel title = new JLabel("jMolecules");
    private final JPanel myMainPanel;

    final JComboBox<String> comboBox = new JComboBox<>(Arrays.stream(ViewChoice.values()).map(ViewChoice::toString)
            .toArray(String[]::new));

    public SettingsView() {
        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(createTitlePanel())
                .addVerticalGap(3)
                .addLabeledComponent("View mode:", comboBox, 1)
                .addVerticalGap(1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();

    }

    private JPanel createTitlePanel() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        titlePanel.add(title);
        return titlePanel;
    }

    public JPanel getPanel() {
        return myMainPanel;
    }


    public int getSelectedIndex() {
        return comboBox.getSelectedIndex();
    }

    public void setSelectedIndex(int newStatus) {
        comboBox.setSelectedIndex(newStatus);
    }

}
