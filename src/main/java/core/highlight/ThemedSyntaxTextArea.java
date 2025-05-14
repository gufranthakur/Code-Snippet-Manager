package core.highlight;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;

import java.util.HashSet;
import java.util.Set;

public class ThemedSyntaxTextArea extends RSyntaxTextArea {

    private static final Set<ThemedSyntaxTextArea> editables = new HashSet<>();

    public static void setTheme(Theme theme) {
        editables.forEach(theme::apply);
    }

    public ThemedSyntaxTextArea(String code) {
        super(code);
        HighlightThemeManager.get().apply(this);
        editables.add(this);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        editables.remove(this);
    }

}
