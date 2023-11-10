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
// ----------------------------------------------------------------------------
// Copyright (C) 2015 Louise A. Dennis and Michael Fisher
//
// This file is part of Gwendolen
//
// Gwendolen is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// Gwendolen is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with Gwendolen if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//----------------------------------------------------------------------------

GWENDOLEN

:name: printagentstate

:Initial Beliefs:

string1("hello")
string2(" ")
string3("world")

:Initial Goals:

compose_strings [perform]

:Plans:

+! compose_strings [perform] : {B string1(S1), B string2(S2), B string3(S3)} <-
	append(S1, S2, Sp),
	printagentstate,
	append(Sp, S3, Sout),
	printstate,
	print(Sout),
	print("hello world");
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


