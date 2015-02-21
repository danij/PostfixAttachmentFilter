package com.dani.attachmentfilter;

public class PostfixHandler {

    public PostfixHandler(String[] args) {
    }

    public void verifyStandardInput() {

        Coordinator coordinator = new Coordinator();

        boolean containsExecutable = coordinator.containsExecutable(System.in);

        if (containsExecutable) {
            System.out.println("Executable found in attachments");
            System.exit(1);
        }
    }
}
