package core.customisation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

public class GithubItem extends JMenu {

    public GithubItem() {
        super("Github");
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(URI.create("https://github.com/gufranthakur/Code-Snippet-Manager"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
}
