package com.dani.attachmentfilter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

public class MessageLoader {

    private Session session;

    public MessageLoader() {

        Properties props = System.getProperties();
        session = Session.getDefaultInstance(props, null);
    }

    public Message load(InputStream stream) throws MessagingException {

        return new MimeMessage(session, stream);
    }

    public Message load(byte[] data) throws MessagingException {

        return load(new ByteArrayInputStream(data));
    }
}
