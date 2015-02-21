package com.dani.attachmentfilter.filters;

import com.dani.attachmentfilter.IContentFilter;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;

public class SevenZipContentFilter implements IContentFilter {

    @Override
    public boolean check(byte[] data, ICoordinator coordinator) {

        if ((data.length < 6) ||
                (data[0] != 0x37) || (data[1] != 0x7A) ||
                (data[2] != (byte)0xBC) || (data[3] != (byte)0xAF) ||
                (data[4] != 0x27) || (data[5] != 0x1C)) {
            return false;
        }

        File tempFile = null;
        SevenZFile sevenZFile = null;

        try {
            tempFile = File.createTempFile("attachment", ".7z");

            FileOutputStream tempStream = new FileOutputStream(tempFile);
            tempStream.write(data);
            tempStream.close();

            sevenZFile = new SevenZFile(tempFile);

            if (null == sevenZFile) {
                return false;
            }

            ArchiveEntry entry;

            while (null != (entry = sevenZFile.getNextEntry())) {

                if (entry.isDirectory()) {
                    continue;
                }

                String name = entry.getName();
                if (coordinator.check(name)) {
                    return true;
                }

                byte entryData[] = new byte[(int)entry.getSize()];
                int read = 0;

                while (read < entryData.length) {
                    read = sevenZFile.read(entryData, read, entryData.length - read);
                }

                if (coordinator.check(entryData)) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (null != sevenZFile) {
                IOUtils.closeQuietly(sevenZFile);
            }
            if (null != tempFile) {
                try {
                    tempFile.delete();
                }
                catch(Exception e2) {
                }
            }
        }

        return false;
    }
}
