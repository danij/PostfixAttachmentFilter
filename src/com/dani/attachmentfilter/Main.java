package com.dani.attachmentfilter;

public class Main {

    public static void main(String[] args) {

        PostfixHandler handler = new PostfixHandler(args);
        handler.verifyStandardInput();
    }
}
