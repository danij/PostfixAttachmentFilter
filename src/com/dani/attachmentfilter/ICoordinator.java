package com.dani.attachmentfilter;

public interface ICoordinator extends ITextFilter {

    boolean check(byte[] data);
}
