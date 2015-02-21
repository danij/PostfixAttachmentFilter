package com.dani.attachmentfilter;

import com.dani.attachmentfilter.filters.ICoordinator;

public interface IContentFilter {

    boolean check(byte[] data, ICoordinator coordinator);
}
