package core.highlight;

import core.io.SettingsManager;
import org.fife.ui.rsyntaxtextarea.Theme;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HighlightThemeManager {

    private static Themes theme = null;

    public static Theme get() {
        final String selected = SettingsManager.get().highlight;
        theme = Themes.valueOf(selected.toUpperCase());
        InputStream inputStream = new ByteArrayInputStream(theme.themeString.getBytes());
        try {
            return Theme.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private enum Themes {

        ECLIPSE("""
                <?xml version="1.0" encoding="UTF-8" ?>
                <!DOCTYPE RSyntaxTheme SYSTEM "theme.dtd">
                
                <!--
                	Theme that mimics Eclipse's defaults.
                	See theme.dtd and org.fife.ui.rsyntaxtextarea.Theme for more information.
                -->
                <RSyntaxTheme version="1.0">
                
                    <!-- Omitting baseFont will use a system-appropriate monospaced. -->
                    <!--<baseFont family="..." size="13"/>-->
                
                    <!--  General editor colors. -->
                    <background color="ffffff"/>
                    <caret color="000000"/>
                    <selection bg="default" fg="default"/>
                    <currentLineHighlight color="e8f2fe" fade="false"/>
                    <marginLine fg="b0b4b9"/>
                    <markAllHighlight color="6b8189"/> <!-- TODO: Fix me -->
                    <markOccurrencesHighlight color="d4d4d4" border="false"/>
                    <matchedBracket fg="c0c0c0" highlightBoth="false" animate="false"/>
                    <hyperlinks fg="0000ff"/>
                    <secondaryLanguages>
                        <language index="1" bg="fff0cc"/>
                        <language index="2" bg="dafeda"/>
                        <language index="3" bg="ffe0f0"/>
                    </secondaryLanguages>
                
                    <!-- Gutter styling. -->
                    <gutterBorder color="dddddd"/>
                    <lineNumbers fg="787878" currentFG="343434"/>
                    <foldIndicator fg="808080" armedFg="585858" iconBg="ffffff"/>
                    <iconRowHeader activeLineRange="3399ff"/>
                
                    <!-- Syntax tokens. -->
                    <tokenStyles>
                        <style token="IDENTIFIER" fg="000000"/>
                        <style token="RESERVED_WORD" fg="7f0055" bold="true"/>
                        <style token="RESERVED_WORD_2" fg="7f0055" bold="true"/>
                        <style token="ANNOTATION" fg="808080"/>
                        <style token="COMMENT_DOCUMENTATION" fg="3f5fbf"/>
                        <style token="COMMENT_EOL" fg="3f7f5f"/>
                        <style token="COMMENT_MULTILINE" fg="3f7f5f"/>
                        <style token="COMMENT_KEYWORD" fg="7F9FBF" bold="true"/>
                        <style token="COMMENT_MARKUP" fg="7f7f9f"/>
                        <style token="DATA_TYPE" fg="7f0055" bold="true"/>
                        <style token="FUNCTION" fg="000000"/>
                        <style token="LITERAL_BOOLEAN" fg="7f0055" bold="true"/>
                        <style token="LITERAL_NUMBER_DECIMAL_INT" fg="000000"/>
                        <style token="LITERAL_NUMBER_FLOAT" fg="000000"/>
                        <style token="LITERAL_NUMBER_HEXADECIMAL" fg="000000"/>
                        <style token="LITERAL_STRING_DOUBLE_QUOTE" fg="2900ff"/>
                        <style token="LITERAL_CHAR" fg="2900ff"/>
                        <style token="LITERAL_BACKQUOTE" fg="2900ff"/>
                        <style token="MARKUP_TAG_DELIMITER" fg="008080"/>
                        <style token="MARKUP_TAG_NAME" fg="3f7f7f"/>
                        <style token="MARKUP_TAG_ATTRIBUTE" fg="7f007f"/>
                        <style token="MARKUP_TAG_ATTRIBUTE_VALUE" fg="2a00ff" italic="true"/>
                        <style token="MARKUP_COMMENT" fg="3f5fbf"/>
                        <style token="MARKUP_DTD" fg="008080"/>
                        <style token="MARKUP_PROCESSING_INSTRUCTION" fg="808080"/>
                        <style token="MARKUP_CDATA" fg="000000"/>
                        <style token="MARKUP_CDATA_DELIMITER" fg="008080"/>
                        <style token="MARKUP_ENTITY_REFERENCE" fg="2a00ff"/>
                        <style token="OPERATOR" fg="000000"/>
                        <style token="PREPROCESSOR" fg="808080"/>
                        <style token="REGEX" fg="008040"/>
                        <style token="SEPARATOR" fg="000000"/>
                        <style token="VARIABLE" fg="ff9900" bold="true"/>
                        <style token="WHITESPACE" fg="000000"/>
                
                        <style token="ERROR_IDENTIFIER" fg="000000" bg="ffcccc"/>
                        <style token="ERROR_NUMBER_FORMAT" fg="000000" bg="ffcccc"/>
                        <style token="ERROR_STRING_DOUBLE" fg="000000" bg="ffcccc"/>
                        <style token="ERROR_CHAR" fg="000000" bg="ffcccc"/>
                    </tokenStyles>
                
                </RSyntaxTheme>
                """),

        IDEA("""
                <?xml version="1.0" encoding="UTF-8" ?>
                <!DOCTYPE RSyntaxTheme SYSTEM "theme.dtd">
                
                <!--
                	Theme that mimics IntelliJ IDEA's defaults.
                	See theme.dtd and org.fife.ui.rsyntaxtextarea.Theme for more information.
                -->
                <RSyntaxTheme version="1.0">
                
                    <!-- Omitting baseFont will use a system-appropriate monospaced. -->
                    <!--<baseFont family="..." size="13"/>-->
                
                    <!--  General editor colors. -->
                    <background color="ffffff" />
                    <caret color="000000" />
                    <selection fg="ffffff" bg="526da5" />
                    <currentLineHighlight color="ffffd7" fade="false" />
                    <marginLine fg="b0b4b9" />
                    <markAllHighlight color="ccccff" />
                    <markOccurrencesHighlight color="ccccff" border="false" />
                    <matchedBracket fg="99ccff" bg="99ccff" highlightBoth="true" animate="false" />
                    <hyperlinks fg="0000ff" />
                    <secondaryLanguages>
                        <language index="1" bg="fff0cc" />
                        <language index="2" bg="dafeda" />
                        <language index="3" bg="ffe0f0" />
                    </secondaryLanguages>
                
                    <!-- Gutter styling. -->
                    <gutterBorder color="dddddd" />
                    <lineNumbers fg="787878" />
                    <foldIndicator fg="808080" armedFg="585858" iconBg="ffffff" />
                    <iconRowHeader activeLineRange="3399ff" />
                
                    <!-- Syntax tokens. -->
                    <tokenStyles>
                        <style token="IDENTIFIER" fg="000000" />
                        <style token="RESERVED_WORD" fg="000080" bold="true" />
                        <style token="RESERVED_WORD_2" fg="000080" bold="true" />
                        <style token="ANNOTATION" fg="808000" />
                        <style token="COMMENT_DOCUMENTATION" fg="808080" italic="true" />
                        <style token="COMMENT_EOL" fg="808080" italic="true" />
                        <style token="COMMENT_MULTILINE" fg="808080" italic="true" />
                        <style token="COMMENT_KEYWORD" fg="808080" bold="true" underline="true" italic="true" />
                        <style token="COMMENT_MARKUP" fg="808080" bg="e2ffe2" italic="true" />
                        <style token="DATA_TYPE" fg="000080" bold="true" />
                        <style token="FUNCTION" fg="000000" />
                        <style token="LITERAL_BOOLEAN" fg="000080" bold="true" />
                        <style token="LITERAL_NUMBER_DECIMAL_INT" fg="0000ff" />
                        <style token="LITERAL_NUMBER_FLOAT" fg="0000ff" />
                        <style token="LITERAL_NUMBER_HEXADECIMAL" fg="0000ff" />
                        <style token="LITERAL_STRING_DOUBLE_QUOTE" fg="008000" bold="true" />
                        <style token="LITERAL_CHAR" fg="008000" bold="true" />
                        <style token="LITERAL_BACKQUOTE" fg="008000" bold="true" />
                        <style token="MARKUP_TAG_DELIMITER" fg="000000" bold="true" />
                        <style token="MARKUP_TAG_NAME" fg="000080" bold="true" />
                        <style token="MARKUP_TAG_ATTRIBUTE" fg="0000ff" bold="true" />
                        <style token="MARKUP_TAG_ATTRIBUTE_VALUE" fg="008000" bold="true" />
                        <style token="MARKUP_COMMENT" fg="808080" italic="true"/>
                        <style token="MARKUP_DTD" fg="808080"/>
                        <style token="MARKUP_PROCESSING_INSTRUCTION"  fg="808080"/>
                        <style token="MARKUP_CDATA" fg="cc6600"/>
                        <style token="MARKUP_CDATA_DELIMITER" fg="008080"/>
                        <style token="MARKUP_ENTITY_REFERENCE" fg="008000"/>
                        <style token="OPERATOR" fg="000000" />
                        <style token="PREPROCESSOR" fg="808080" />
                        <style token="REGEX" fg="008040" />
                        <style token="SEPARATOR" fg="000000" />
                        <style token="VARIABLE" fg="810ca8" bold="true" />
                        <style token="WHITESPACE" fg="000000" />
                
                        <style token="ERROR_IDENTIFIER" fg="ff0000" />
                        <style token="ERROR_NUMBER_FORMAT" fg="ff0000" />
                        <style token="ERROR_STRING_DOUBLE" fg="ff0000" />
                        <style token="ERROR_CHAR" fg="ff0000" />
                    </tokenStyles>
                
                </RSyntaxTheme>
                """),

        VS("""
                <?xml version="1.0" encoding="UTF-8" ?>
                <!DOCTYPE RSyntaxTheme SYSTEM "theme.dtd">
                
                <!--
                	Theme that mimics Visual Studio's defaults.
                	See theme.dtd and org.fife.ui.rsyntaxtextarea.Theme for more information.
                -->
                <RSyntaxTheme version="1.0">
                
                    <!-- Omitting baseFont will use a system-appropriate monospaced. -->
                    <!--<baseFont family="..." size="13"/>-->
                
                    <!--  General editor colors. -->
                    <background color="ffffff"/>
                    <caret color="000000"/>
                    <selection fg="default" bg="default"/>
                    <currentLineHighlight color="e8f2fe" fade="false"/>
                    <marginLine fg="b0b4b9"/>
                    <markAllHighlight color="6b8189"/> <!-- TODO: Fix me -->
                    <markOccurrencesHighlight color="d4d4d4" border="false"/> <!-- TODO: Fix me -->
                    <matchedBracket fg="DBE0CC" highlightBoth="false" animate="false"/>
                    <hyperlinks fg="0000ff"/>
                    <secondaryLanguages>
                        <language index="1" bg="fff0cc"/>
                        <language index="2" bg="dafeda"/>
                        <language index="3" bg="ffe0f0"/>
                    </secondaryLanguages>
                
                
                    <!-- Gutter styling. -->
                    <gutterBorder color="808080"/>
                    <lineNumbers fg="2B91AF"/>
                    <foldIndicator fg="808080" armedFg="585858" iconBg="ffffff"/>
                    <iconRowHeader activeLineRange="3399ff"/>
                
                    <!-- Syntax tokens. -->
                    <tokenStyles>
                        <style token="IDENTIFIER" fg="000000"/>
                        <style token="RESERVED_WORD" fg="0000ff" bold="true"/>
                        <style token="RESERVED_WORD_2" fg="0000ff" bold="true"/>
                        <style token="ANNOTATION" fg="808080"/>
                        <style token="COMMENT_DOCUMENTATION" fg="008000"/>
                        <style token="COMMENT_EOL" fg="008000"/>
                        <style token="COMMENT_MULTILINE" fg="008000"/>
                        <style token="COMMENT_KEYWORD" fg="ae9fbf"/>
                        <style token="COMMENT_MARKUP" fg="808080"/>
                        <style token="DATA_TYPE" fg="0000ff" bold="true"/>
                        <style token="FUNCTION" fg="000000"/>
                        <style token="LITERAL_BOOLEAN" fg="0000ff" bold="true"/>
                        <style token="LITERAL_NUMBER_DECIMAL_INT" fg="000000"/>
                        <style token="LITERAL_NUMBER_FLOAT" fg="000000"/>
                        <style token="LITERAL_NUMBER_HEXADECIMAL" fg="000000"/>
                        <style token="LITERAL_STRING_DOUBLE_QUOTE" fg="A31515"/>
                        <style token="LITERAL_CHAR" fg="A31515"/>
                        <style token="LITERAL_BACKQUOTE" fg="A31515"/>
                        <style token="MARKUP_TAG_DELIMITER" fg="0000ff"/>
                        <style token="MARKUP_TAG_NAME" fg="A31515"/>
                        <style token="MARKUP_TAG_ATTRIBUTE" fg="ff0000"/>
                        <style token="MARKUP_TAG_ATTRIBUTE_VALUE" fg="0000ff"/>
                        <style token="MARKUP_COMMENT" fg="006000" italic="true"/>
                        <style token="MARKUP_DTD" fg="ad8000"/>
                        <style token="MARKUP_PROCESSING_INSTRUCTION" fg="808080"/>
                        <style token="MARKUP_CDATA" fg="000000"/>
                        <style token="MARKUP_CDATA_DELIMITER" fg="0000ff"/>
                        <style token="OPERATOR" fg="000000"/>
                        <style token="PREPROCESSOR" fg="808080"/>
                        <style token="REGEX" fg="000000"/>
                        <style token="SEPARATOR" fg="000000"/>
                        <style token="VARIABLE" fg="ff9900" bold="true"/>
                        <style token="WHITESPACE" fg="000000"/>
                
                        <style token="ERROR_IDENTIFIER" fg="000000" bg="ffcccc"/>
                        <style token="ERROR_NUMBER_FORMAT" fg="000000" bg="ffcccc"/>
                        <style token="ERROR_STRING_DOUBLE" fg="000000" bg="ffcccc"/>
                        <style token="ERROR_CHAR" fg="000000" bg="ffcccc"/>
                    </tokenStyles>
                
                </RSyntaxTheme>
                """),

        MONOKAI("""
                <?xml version="1.0" encoding="UTF-8" ?>
                <!DOCTYPE RSyntaxTheme SYSTEM "theme.dtd">
                
                <!-- "Monokai" inspired theme for use with RSyntaxTextArea. -->
                
                <RSyntaxTheme version="1.0">
                
                    <!--  General editor colors. -->
                    <background color="2E2E2E"/>
                    <caret color="D2D2D2"/>
                    <selection useFG="false" bg="4C4C4C" roundedEdges="false"/>
                    <currentLineHighlight color="383A38" fade="false"/>
                    <marginLine fg="394448"/>
                    <markAllHighlight color="383A38"/>
                    <markOccurrencesHighlight color="383A38" border="false"/>
                    <matchedBracket fg="F0F0F0" bg="2E2E2E" highlightBoth="true" animate="true"/>
                    <hyperlinks fg="A082BD"/>
                    <secondaryLanguages>
                        <language index="1" bg="333344"/>
                        <language index="2" bg="223322"/>
                        <language index="3" bg="332222"/>
                    </secondaryLanguages>
                
                    <!-- Gutter styling. -->
                    <gutterBorder color="81969A"/>
                    <lineNumbers fg="81969A" currentFG="F9F9F9"/>
                    <foldIndicator fg="6A8088" armedFg="a9b7c6" iconBg="2f383c" iconArmedBg="3f484c"/>
                    <iconRowHeader activeLineRange="878787"/>
                
                    <!-- Syntax tokens. -->
                    <tokenStyles>
                        <style token="IDENTIFIER" fg="F9F9F9"/>
                        <style token="RESERVED_WORD" fg="F92672" bold="false"/>
                        <style token="RESERVED_WORD_2" fg="A6E22E" bold="true"/>
                        <style token="ANNOTATION" fg="FD971F"/>
                        <style token="COMMENT_DOCUMENTATION" fg="878787"/>
                        <style token="COMMENT_EOL" fg="6C6C6C"/>
                        <style token="COMMENT_MULTILINE" fg="93a1a1"/>
                        <style token="COMMENT_KEYWORD" fg="FD971F"/>
                        <style token="COMMENT_MARKUP" fg="FD971F"/>
                        <style token="FUNCTION" fg="52E3F6"/>
                        <style token="DATA_TYPE" fg="52E3F6" bold="true"/>
                        <style token="LITERAL_BOOLEAN" fg="FD971F" bold="true"/>
                        <style token="LITERAL_NUMBER_DECIMAL_INT" fg="AE81FF"/>
                        <style token="LITERAL_NUMBER_FLOAT" fg="AE81FF"/>
                        <style token="LITERAL_NUMBER_HEXADECIMAL" fg="AE81FF"/>
                        <style token="LITERAL_STRING_DOUBLE_QUOTE" fg="E3ED77"/>
                        <style token="LITERAL_CHAR" fg="A6E22E"/>
                        <style token="LITERAL_BACKQUOTE" fg="EC7600"/>
                        <style token="MARKUP_TAG_DELIMITER" fg="F92672"/>
                        <style token="MARKUP_TAG_NAME" fg="ABBFD3" bold="true"/>
                        <style token="MARKUP_TAG_ATTRIBUTE" fg="B3B689"/>
                        <style token="MARKUP_TAG_ATTRIBUTE_VALUE" fg="e1e2cf"/>
                        <style token="MARKUP_COMMENT" fg="878787"/>
                        <style token="MARKUP_DTD" fg="A082BD"/>
                        <style token="MARKUP_PROCESSING_INSTRUCTION" fg="A082BD"/>
                        <style token="MARKUP_CDATA" fg="d5e6f0"/>
                        <style token="MARKUP_CDATA_DELIMITER" fg="FD971F"/>
                        <style token="MARKUP_ENTITY_REFERENCE" fg="F92672"/>
                        <style token="OPERATOR" fg="F92672"/>
                        <style token="PREPROCESSOR" fg="A082BD"/>
                        <style token="REGEX" fg="AE81FF"/>
                        <style token="SEPARATOR" fg="F0F0F0"/>
                        <style token="VARIABLE" fg="FD971F" bold="false"/>
                        <style token="WHITESPACE" fg="F9F9F9"/>
                
                        <style token="ERROR_IDENTIFIER" fg="F9F9F9" bg="d82323"/>
                        <style token="ERROR_NUMBER_FORMAT" fg="F9F9F9" bg="d82323"/>
                        <style token="ERROR_STRING_DOUBLE" fg="F9F9F9" bg="d82323"/>
                        <style token="ERROR_CHAR" fg="F9F9F9" bg="d82323"/>
                    </tokenStyles>
                
                </RSyntaxTheme>
                """),

        OBSIDIAN("""
                <?xml version="1.0" encoding="UTF-8" ?>
                <!DOCTYPE RSyntaxTheme SYSTEM "theme.dtd">
                
                <!--
                	Dark theme based off of Notepad++'s Obsidian theme.
                	See theme.dtd and org.fife.ui.rsyntaxtextarea.Theme for more information.
                -->
                <RSyntaxTheme version="1.0">
                
                    <!-- Omitting baseFont will use a system-appropriate monospaced. -->
                    <!--<baseFont family="..." size="13"/>-->
                
                    <!--  General editor colors. -->
                    <background color="293134" />
                    <caret color="c1cbc2" />
                    <selection useFG="false" bg="404E51" roundedEdges="false" />
                    <currentLineHighlight color="2F393C" fade="false" />
                    <marginLine fg="394448" />
                    <markAllHighlight color="6b8189" />
                    <!-- TODO: Fix me -->
                    <markOccurrencesHighlight color="5b7179" border="false" />
                    <matchedBracket fg="6A8088" bg="6b8189" highlightBoth="false" animate="true" />
                    <hyperlinks fg="a082bd" />
                    <secondaryLanguages>
                        <language index="1" bg="333344" />
                        <language index="2" bg="223322" />
                        <language index="3" bg="332222" />
                    </secondaryLanguages>
                
                    <!-- Gutter styling. -->
                    <gutterBorder color="81969A" />
                    <lineNumbers fg="81969A" currentFG="a9b7c6"/>
                    <foldIndicator fg="6A8088" armedFg="a9b7c6" iconBg="2f383c" iconArmedBg="3f484c" />
                    <iconRowHeader activeLineRange="3399ff" />
                
                    <!-- Syntax tokens. -->
                    <tokenStyles>
                        <style token="IDENTIFIER" fg="a9b7c6" />
                        <style token="RESERVED_WORD" fg="93C763" bold="true" />
                        <style token="RESERVED_WORD_2" fg="93C763" bold="true" />
                        <style token="ANNOTATION" fg="E8E2B7" />
                        <style token="COMMENT_DOCUMENTATION" fg="6C788C" />
                        <style token="COMMENT_EOL" fg="66747B" />
                        <style token="COMMENT_MULTILINE" fg="66747B" />
                        <style token="COMMENT_KEYWORD" fg="ae9fbf" />
                        <style token="COMMENT_MARKUP" fg="ae9fbf" />
                        <style token="FUNCTION" fg="E0E2E4" />
                        <style token="DATA_TYPE" fg="678CB1" bold="true" />
                        <style token="LITERAL_BOOLEAN" fg="93C763" bold="true" />
                        <style token="LITERAL_NUMBER_DECIMAL_INT" fg="FFCD22" />
                        <style token="LITERAL_NUMBER_FLOAT" fg="FFCD22" />
                        <style token="LITERAL_NUMBER_HEXADECIMAL" fg="FFCD22" />
                        <style token="LITERAL_STRING_DOUBLE_QUOTE" fg="EC7600" />
                        <style token="LITERAL_CHAR" fg="EC7600" />
                        <style token="LITERAL_BACKQUOTE" fg="EC7600" />
                        <style token="MARKUP_TAG_DELIMITER" fg="678CB1" />
                        <style token="MARKUP_TAG_NAME" fg="ABBFD3" bold="true" />
                        <style token="MARKUP_TAG_ATTRIBUTE" fg="B3B689" />
                        <style token="MARKUP_TAG_ATTRIBUTE_VALUE" fg="e1e2cf" />
                        <style token="MARKUP_COMMENT" fg="66747B" />
                        <style token="MARKUP_DTD" fg="A082BD" />
                        <style token="MARKUP_PROCESSING_INSTRUCTION" fg="A082BD" />
                        <style token="MARKUP_CDATA" fg="d5e6f0" />
                        <style token="MARKUP_CDATA_DELIMITER" fg="ae9fbf" />
                        <style token="MARKUP_ENTITY_REFERENCE" fg="678CB1" />
                        <style token="OPERATOR" fg="E8E2B7" />
                        <style token="PREPROCESSOR" fg="A082BD" />
                        <style token="REGEX" fg="d39745" />
                        <style token="SEPARATOR" fg="E8E2B7" />
                        <style token="VARIABLE" fg="ae9fbf" bold="true" />
                        <style token="WHITESPACE" fg="E0E2E4" />
                
                        <style token="ERROR_IDENTIFIER" fg="E0E2E4" bg="04790e" />
                        <style token="ERROR_NUMBER_FORMAT" fg="E0E2E4" bg="04790e" />
                        <style token="ERROR_STRING_DOUBLE" fg="E0E2E4" bg="04790e" />
                        <style token="ERROR_CHAR" fg="E0E2E4" bg="04790e" />
                    </tokenStyles>
                
                </RSyntaxTheme>
                """);

        public final String themeString;

        Themes(String themeString) {
            this.themeString = themeString;
        }

    }
}
