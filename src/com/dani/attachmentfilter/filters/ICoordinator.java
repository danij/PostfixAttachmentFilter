package com.dani.attachmentfilter.filters;

import com.dani.attachmentfilter.IContentFilter;
import com.dani.attachmentfilter.ITextFilter;

public interface ICoordinator extends ITextFilter {

    boolean check(byte[] data);
}
