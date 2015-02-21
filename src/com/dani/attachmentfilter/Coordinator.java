package com.dani.attachmentfilter;

import com.dani.attachmentfilter.filters.*;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class Coordinator implements ICoordinator {

    private MSGContentFilter msgFilter;
    private IContentFilter contentFilters[];
    private ITextFilter textFilters[];

    public Coordinator() {

        msgFilter = new MSGContentFilter();

        contentFilters = new IContentFilter[] {
                new PEContentFilter(),
                new ApacheArchiveContentFilter(),
                new SingleFileArchiveContentFilter(),
                new SevenZipContentFilter(),
                msgFilter
        };

        textFilters = new ITextFilter[] {
                new WindowsExecutableExtensionFilter()
        };
    }

    @Override
    public boolean check(byte[] data) {

        for (IContentFilter filter : contentFilters) {
            if (filter.check(data, this)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean check(String value) {

        for (ITextFilter filter : textFilters) {
            if (filter.check(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsExecutable(InputStream stream) {

        byte[] data;

        try {
            data = IOUtils.toByteArray(stream);
        } catch (IOException e) {
            return false;
        }

        return containsExecutable(data);
    }

    public boolean containsExecutable(byte[] data) {

        return msgFilter.check(data, this);
    }
}
