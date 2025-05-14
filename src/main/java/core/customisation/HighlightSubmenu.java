package core.customisation;

import core.highlight.HighlightThemeManager;
import core.highlight.ThemedSyntaxTextArea;
import core.io.SettingsManager;
import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class HighlightSubmenu extends JMenu implements MenuClickHandler {

    private final Set<ClickableItem> clickables = new HashSet<>();

    public HighlightSubmenu() {
        super("Highlight");
        this.add(new ClickableItem("Eclipse", this));
        this.add(new ClickableItem("Idea", this));
        this.add(new ClickableItem("VS", this));
        this.add(new ClickableItem("Monokai", this));
        this.add(new ClickableItem("Obsidian", this));
        redrawSelectionStatus();
    }

    @Override
    public JMenuItem add(JMenuItem item) {
        super.add(item);
        if (item instanceof ClickableItem clickable) {
            clickables.add(clickable);
        }
        return item;
    }

    @Override
    public void click(ClickableItem item) {
        SettingsManager.get().highlight = item.name;
        Theme theme = HighlightThemeManager.get();
        ThemedSyntaxTextArea.setTheme(theme);
        redrawSelectionStatus();
    }

    private void redrawSelectionStatus() {
        String selected = SettingsManager.get().highlight;
        clickables.forEach(clickable -> clickable.setText((clickable.name.equals(selected) ? "✓" : " ") + clickable.name));
    }

}
