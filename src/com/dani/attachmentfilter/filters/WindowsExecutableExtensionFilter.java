package com.dani.attachmentfilter.filters;

import com.dani.attachmentfilter.ITextFilter;

public class WindowsExecutableExtensionFilter implements ITextFilter {

    private static final String extensions[] = {
            ".exe",
            ".bat",
            ".com",
            ".scr",
            ".dll",
            ".sys",
            ".jar",
            ".cmd",
            ".lnk",
            ".reg",
            ".pif",
            ".vbs",
            ".vbe",
            ".vbscript",
            ".ws",
            ".wsf",
            ".msi",
            ".msp",
            ".ins",
            ".inx",
            ".action",
            ".shs",
            ".ws",
            ".wsf",
            ".run"
    };

    @Override
    public boolean check(String value) {

        if (null == value) {
            return false;
        }

        value = value.toLowerCase();

        for (String item : extensions) {
            if (value.endsWith(item)) {
                return true;
            }
        }

        return false;
    }
}
