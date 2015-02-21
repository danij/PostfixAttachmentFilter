package com.dani.attachmentfilter;

public interface IContentFilter {

    boolean check(byte[] data, ICoordinator coordinator);
}
