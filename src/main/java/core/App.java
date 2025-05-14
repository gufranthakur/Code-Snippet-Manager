package core;

import core.customisation.GithubItem;
import core.customisation.ThemeMenu;
import core.highlight.GeneralThemeManager;
import core.io.DataManager;
import core.io.SettingsManager;
import core.panels.ControlPanel;
import core.panels.GroupsTabbedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends JFrame {

    public ControlPanel controlPanel;
    public GroupsTabbedPanel tabbedPanel;
    public DataManager dataManager;

    private App() {
        this.setTitle("Code Snippet Manager");
        this.setSize(640, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
    }

    private void init() {
        controlPanel = new ControlPanel(this);
        tabbedPanel = new GroupsTabbedPanel(this);

        tabbedPanel.init();

        dataManager = new DataManager(tabbedPanel);
        dataManager.loadData();

        controlPanel.init();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new ThemeMenu(this));
        menuBar.add(new GithubItem());
        setJMenuBar(menuBar);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataManager.saveData();
                SettingsManager.save();
            }
        });
    }

    private void addComponent() {
        controlPanel.addComponent();
        tabbedPanel.addComponent();

        this.setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        SettingsManager.load();
        UIManager.setLookAndFeel(GeneralThemeManager.get());
        UIManager.put("List.lockToPositionOnScroll", Boolean.FALSE);

        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.init();
            app.addComponent();
        });
    }

}