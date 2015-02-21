package com.dani.attachmentfilter.filters;

import com.dani.attachmentfilter.IContentFilter;

public class PEContentFilter implements IContentFilter {

    @Override
    public boolean check(byte[] data, ICoordinator coordinator) {

        //check the magic field of the IMAGE_DOS_HEADER (MZ)
        return data.length > 2 && data[0] == 0x4D && data[1] == 0x5A;
    }
}
