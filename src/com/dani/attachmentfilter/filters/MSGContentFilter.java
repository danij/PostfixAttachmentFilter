package com.dani.attachmentfilter.filters;

import com.dani.attachmentfilter.IContentFilter;
import com.dani.attachmentfilter.ICoordinator;
import com.dani.attachmentfilter.MessageLoader;
import org.apache.commons.io.IOUtils;

import javax.mail.*;
import java.io.IOException;

public class MSGContentFilter implements IContentFilter {

    @Override
    public boolean check(byte[] data, ICoordinator coordinator) {

        Message message;
        MessageLoader loader = new MessageLoader();

        try {
            message = loader.load(data);
        } catch (MessagingException e) {
            return false;
        }

        if (null == message) {
            return false;
        }

        try {
            return check(message, coordinator);
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean check(Message message, ICoordinator coordinator) throws IOException, MessagingException {

        Multipart multiPart = (Multipart)message.getContent();
        if (null != multiPart) {
            return check(multiPart, coordinator);
        }

        return false;
    }

    protected boolean check(Multipart multipart, ICoordinator coordinator) throws MessagingException, IOException {

        for (int i = 0; i < multipart.getCount(); i++) {

            BodyPart part = multipart.getBodyPart(i);

            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {

                String name = part.getFileName();
                if (coordinator.check(name)) {
                    return true;
                }

                byte[] bytes = IOUtils.toByteArray(part.getInputStream());
                if (coordinator.check(bytes)) {
                    return true;
                }
            }
        }

        return false;
    }
}
