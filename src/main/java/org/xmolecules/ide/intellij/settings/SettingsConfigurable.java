package org.xmolecules.ide.intellij.settings;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;

final class SettingsConfigurable implements Configurable {

    private SettingsView jmoleculesView;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "jMolecules";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        jmoleculesView = new SettingsView();
        return jmoleculesView.getPanel();
    }

    @Override
    public boolean isModified() {
        SettingsState settings = SettingsState.getInstance();
        return jmoleculesView.getSelectedIndex() != settings.viewIndex;
    }

    @Override
    public void apply() {
        SettingsState settings = SettingsState.getInstance();
        settings.viewIndex = jmoleculesView.getSelectedIndex();

         Arrays.stream(ProjectManager.getInstance().getOpenProjects()).forEach(
                 project -> ProjectView.getInstance(project).refresh());
    }

    @Override
    public void reset() {
        SettingsState settings = SettingsState.getInstance();
        jmoleculesView.setSelectedIndex(settings.viewIndex);
    }

    @Override
    public void disposeUIResources() {
        jmoleculesView = null;
    }

}
