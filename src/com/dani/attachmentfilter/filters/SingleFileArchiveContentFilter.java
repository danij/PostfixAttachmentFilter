package com.dani.attachmentfilter.filters;

import com.dani.attachmentfilter.IContentFilter;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.zip.GZIPInputStream;

public class SingleFileArchiveContentFilter implements IContentFilter {

    protected static final Class streamTypes[] = new Class[] {
            GZIPInputStream.class,
            BZip2CompressorInputStream.class,
            XZCompressorInputStream.class,
            ZCompressorInputStream.class,
            LZMACompressorInputStream.class,
            Pack200CompressorInputStream.class,
    };

    protected InputStream CreateInstance(Class type, InputStream stream) {

        for (Constructor ctor : type.getDeclaredConstructors()) {
            if (ctor.getParameterTypes().length == 1 && ctor.getParameterTypes()[0] == InputStream.class) {

                try {
                    return (InputStream)(ctor.newInstance(stream));
                } catch (Exception e) {
                }
            }
        }

        return null;
    }

    @Override
    public boolean check(byte[] data, ICoordinator coordinator) {

        for (Class item : streamTypes) {

            try {
                InputStream stream = CreateInstance(item, new ByteArrayInputStream(data));
                if (null == stream) {
                    continue;
                }
                byte[] uncompressedData = IOUtils.toByteArray(stream);

                return coordinator.check(uncompressedData);
            } catch (Exception e) {
            }
        }

        return false;
    }
}
