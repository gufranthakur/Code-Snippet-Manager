package core.highlight;

import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import core.io.SettingsManager;

import javax.swing.plaf.basic.BasicLookAndFeel;

public class GeneralThemeManager {

    public static BasicLookAndFeel get() {
        return switch (SettingsManager.get().general) {
            case "Arc" -> new FlatArcIJTheme();
            case "Light Flat" -> new FlatLightFlatIJTheme();
            case "Light Solarized" -> new FlatSolarizedLightIJTheme();
            case "One Dark" -> new FlatOneDarkIJTheme();
            case "Arc Dark" -> new FlatArcDarkIJTheme();
            case "Nord" -> new FlatNordIJTheme();
            case "Monokai" -> new FlatMonokaiProIJTheme();
            case "Mac Dark" -> new FlatMacDarkLaf();
            default -> null; // this will never happen
        };
    }

}
