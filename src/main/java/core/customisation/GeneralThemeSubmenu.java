package core.customisation;

import core.App;
import core.io.SettingsManager;
import core.utils.RuntimeSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;

public class GeneralThemeSubmenu extends JMenu implements MenuClickHandler {

    private final App app;

    public GeneralThemeSubmenu(App app) {
        super("General");
        this.app = app;
        this.add(new ClickableItem("Arc", this));
        this.add(new ClickableItem("Light Flat", this));
        this.add(new ClickableItem("Light Solarized", this));
        this.add(new ClickableItem("Arc Dark", this));
        this.add(new ClickableItem("One Dark", this));
        this.add(new ClickableItem("Nord", this));
        this.add(new ClickableItem("Monokai", this));
        this.add(new ClickableItem("Mac Dark", this));
    }

    @Override
    public JMenuItem add(JMenuItem item) {
        super.add(item);
        if (item instanceof ClickableItem clickable) {
            String selected = SettingsManager.get().general;
            clickable.setText((selected.equals(clickable.name) ? "✓" : " ") + clickable.name);
        }
        return item;
    }

    @Override
    public void click(ClickableItem item) {
        MenuSelectionManager.defaultManager().clearSelectedPath();
        SettingsManager.get().general = item.name;
        int response = JOptionPane.showConfirmDialog(
                this,
                "For a theme to change, the app must relaunch.",
                "Relaunch now?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.CANCEL_OPTION)
            return;

        try {
            String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            File jarFile = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            ProcessBuilder builder = switch (RuntimeSource.get()) {
                case JAR -> new ProcessBuilder(javaBin, "-jar", jarFile.getPath());
                case CLASSES -> new ProcessBuilder(javaBin, "-cp", System.getProperty("java.class.path"), App.class.getName());
            };

            // this ensures the app saves all data as if closed naturally
            WindowEvent wev = new WindowEvent(app, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

            builder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
