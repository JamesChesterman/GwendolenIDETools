package Language;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;


final class GwendolenColourSettings implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Titles", GwendolenSyntaxHighlighter.GROUP1),
            new AttributesDescriptor("Comments", GwendolenSyntaxHighlighter.GROUP2),
            new AttributesDescriptor("Operations", GwendolenSyntaxHighlighter.GROUP3),
            new AttributesDescriptor("Keywords", GwendolenSyntaxHighlighter.GROUP4),
            new AttributesDescriptor("Blocks", GwendolenSyntaxHighlighter.GROUP5),
            new AttributesDescriptor("Variables and Constants", GwendolenSyntaxHighlighter.GROUP6),
            new AttributesDescriptor("Strings", GwendolenSyntaxHighlighter.GROUP7),
            new AttributesDescriptor("Numbers", GwendolenSyntaxHighlighter.GROUP8),
            new AttributesDescriptor("Brackets", GwendolenSyntaxHighlighter.GROUP9),
            new AttributesDescriptor("Bad value", GwendolenSyntaxHighlighter.BAD_CHARACTER)
    };

    @Nullable
    @Override
    public Icon getIcon(){
        return GwendolenIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter(){
        return new GwendolenSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText(){
        return """
//This is a comment at the start of the program
GWENDOLEN

:name: robot

:Initial Beliefs:
Gone
Went
Skipped

:Initial Goals:

goto55 [perform]

:Plans:

+!goto55 [perform] : {True} <- move_to(5,5);

+rubble(5, 5): {True} <- +holding(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z);

+wentTo : {B Gone} <- print(wentTo);
-wentTo : {~B Gone} <- print("Not went to");

+gone : {~B Went} <- print(Gone);
-gone : {B Went} <- print("Not Gone");

+holding(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z): {True} <- print(done);

+skipped : {B Skipped} <- print(Skip);
-skipped : {~B Skipped, B Gone} <- print(Skip);
                """;
    }

    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap(){
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors(){
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors(){
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName(){
        return "Gwendolen";
    }


}


