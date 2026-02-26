package com.subex.automation.helpers.file;

import java.io.File;

public class SortFiles implements Comparable<Object> {
    public long t;
    public File f;

    public SortFiles(File file) {
        f = file;
        t = file.lastModified();
    }

    public int compareTo(Object o) {
        long u = ((SortFiles) o).t;
        return t < u ? -1 : t == u ? 0 : 1;
    }
};