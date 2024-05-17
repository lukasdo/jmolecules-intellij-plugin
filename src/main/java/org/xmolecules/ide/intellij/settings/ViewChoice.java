package org.xmolecules.ide.intellij.settings;

public enum ViewChoice {
    NODES("As Nodes"),
    LABELS("As Labels");

    private final String name;

    ViewChoice(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
