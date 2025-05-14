package core.objects;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import core.highlight.LanguageDetector;
import core.highlight.HighlightThemeManager;
import core.highlight.ThemedSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.geom.Rectangle2D;

public class Snippet extends JPanel {

    public Group group;

    private final EditableLabel nameLabel;
    private final JToggleButton editToggle;
    private final Dimension buttonDimension = new Dimension(80, 35);

    private RSyntaxTextArea snippetTextArea;
    private final Dimension rolledSize = new Dimension(400, 200);
    private final LineCounter lineCounter;

    private final LanguageDetector languageDetector = new LanguageDetector();

    public Snippet(Group group, String snippetName, String snippetCode, boolean newlyCreated) {
        this.group = group;

        this.setLayout(new BorderLayout());
        this.setBackground(group.getBackground().brighter());
        this.setBorder(new EmptyBorder(0, 10, 10, 10));
        this.setPreferredSize(rolledSize);
        this.putClientProperty(FlatClientProperties.STYLE, "arc : 20");

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setPreferredSize(new Dimension(this.getPreferredSize().width, 70));

        final Snippet snippet = this;
        nameLabel = new EditableLabel(snippetName, 20, 120, 50) {
            @Override
            public LabelEditResult finishEdit(String oldText, String newText) {
                if (newText.isBlank()) {
                    return new LabelEditResult.Error("Name blank", "Snippet name cannot be blank");
                } else if (group.snippets.stream().anyMatch(s -> s != snippet && s.getName().equalsIgnoreCase(newText))) {
                    return new LabelEditResult.Error("Duplicate name", "Two snippet names cannot be identical");
                } else {
                    snippetTextArea.setSyntaxEditingStyle(languageDetector.get(newText));
                    return new LabelEditResult.Success();
                }
            }
        };
        nameLabel.setFont(new Font(FlatInterFont.FAMILY, Font.PLAIN, 20));
        nameLabel.setPreferredSize(new Dimension(160, 90));

        JButton copyButton = new JButton("Copy");
        copyButton.setPreferredSize(buttonDimension);
        copyButton.setMaximumSize(buttonDimension);
        copyButton.setBackground(new Color(46, 97, 234));
        copyButton.addActionListener(e -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            StringSelection selection = new StringSelection(getText());
            clipboard.setContents(selection, null);
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(buttonDimension);
        deleteButton.setMaximumSize(buttonDimension);
        deleteButton.setBackground(new Color(148, 24, 24));
        deleteButton.addActionListener(e -> deleteSnippet());

        editToggle = new JToggleButton("Edit");
        editToggle.setPreferredSize(buttonDimension);
        editToggle.setMaximumSize(buttonDimension);
        editToggle.setBackground(new Color(176, 122, 5));
        editToggle.addActionListener(e -> {
            if (editToggle.isSelected()) {
                editToggle.setText("Shrink");
                snippetTextArea.setEditable(true);
            } else {
                editToggle.setText("Edit");
                snippetTextArea.setEditable(false);
            }
            resize();
            revalidate();
        });

        snippetTextArea = new ThemedSyntaxTextArea(snippetCode);
        snippetTextArea.setEditable(false);
        snippetTextArea.setAutoIndentEnabled(true);
        snippetTextArea.setSyntaxEditingStyle(languageDetector.get(getName()));
        snippetTextArea.setPreferredSize(rolledSize);
        HighlightThemeManager.get().apply(snippetTextArea);
        snippetTextArea.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void updateText() {
                snippetTextArea.revalidate();
                lineCounter.render(snippetTextArea.getLineCount());
                lineCounter.revalidate();
                revalidate();
                resize();
            }
        });

        lineCounter = new LineCounter();
        lineCounter.render(snippetTextArea.getLineCount());

        JPanel textAreasHolder = new JPanel();
        textAreasHolder.setLayout(new BoxLayout(textAreasHolder, BoxLayout.X_AXIS));
        textAreasHolder.add(lineCounter);
        textAreasHolder.add(snippetTextArea);

        controlPanel.add(nameLabel);
        controlPanel.add(deleteButton);
        controlPanel.add(copyButton);
        controlPanel.add(editToggle);

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(textAreasHolder, BorderLayout.CENTER);

        if (newlyCreated)
            nameLabel.setEditable();
    }

    public String getName() {
        return nameLabel.getName();
    }

    public String getText() {
        return snippetTextArea.getText();
    }

    private void deleteSnippet() {
        int confirmed = !getText().isBlank() ? JOptionPane.showConfirmDialog(null,
                "Deleted snippets cannot be restored",
                "Delete snippet?", JOptionPane.YES_NO_OPTION) : JOptionPane.YES_OPTION;

        if (confirmed == JOptionPane.YES_OPTION) {
            group.snippets.remove(this);
            group.remove(this);
            group.revalidate();
            group.repaint();
        }

    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        if (snippetTextArea != null) {
            snippetTextArea.setPreferredSize(rolledSize);
        }
        super.setPreferredSize(preferredSize);
    }

    private String longestLine() {
        String[] lines = getText().split("\\r?\\n");
        String longestLine = "";
        for (String line : lines) {
            if (line.length() > longestLine.length()) {
                longestLine = line;
            }
        }
        return longestLine;
    }

    private boolean editMode() {
        return editToggle.isSelected();
    }

    private void resize() {
        if (editMode()) {
            FontMetrics fm = snippetTextArea.getFontMetrics(snippetTextArea.getFont());
            Rectangle2D bounds = fm.getStringBounds(longestLine(), getGraphics());
            int width = Math.max((int) bounds.getWidth() + 80 + lineCounter.getWidth(), rolledSize.width);
            int height = Math.max(snippetTextArea.getLineHeight() * snippetTextArea.getLineCount() + 100, rolledSize.height);
            setPreferredSize(new Dimension(width,height));
        } else {
            setPreferredSize(rolledSize);
        }
    }

}
