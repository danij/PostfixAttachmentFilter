package com.dani.attachmentfilter.filters;

import com.dani.attachmentfilter.IContentFilter;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ApacheArchiveContentFilter implements IContentFilter {

    @Override
    public boolean check(byte[] data, ICoordinator coordinator) {

        ArchiveInputStream stream = null;

        try {
            stream = new ArchiveStreamFactory().createArchiveInputStream(new ByteArrayInputStream(data));
        } catch (ArchiveException e) {
        }

        if (null == stream) {
            return false;
        }

        ArchiveEntry entry;

        try {
            while(null != (entry = stream.getNextEntry())) {

                if (entry.isDirectory()) {
                    continue;
                }

                String name = entry.getName();
                if (coordinator.check(name)) {
                    return true;
                }

                byte[] entryData = IOUtils.toByteArray(stream);

                if (coordinator.check(entryData)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }
}
