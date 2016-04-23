/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import java.awt.TrayIcon.*;
import javax.swing.*;

public class MessageTypeModel extends AbstractListModel implements ComboBoxModel {

    private MessageTypeWrapper[] messageTypeWrappers;
    private Object selectedItem;

    public MessageTypeModel() {
        messageTypeWrappers = new MessageTypeWrapper[]{
                    new MessageTypeWrapper("None", MessageType.NONE),
                    new MessageTypeWrapper("Information", MessageType.INFO),
                    new MessageTypeWrapper("Warning", MessageType.WARNING),
                    new MessageTypeWrapper("Error", MessageType.ERROR)};
    }

    public Object getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Object item) {
        selectedItem = item;
    }

    public Object getElementAt(int index) {
        try {
            return messageTypeWrappers[index];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getSize() {
        return messageTypeWrappers.length;
    }

    public MessageTypeWrapper getMessageTypeWrapper(MessageType messageType) {
        for (MessageTypeWrapper wrapper : messageTypeWrappers) {
            if (wrapper.getMessageType().equals(messageType)) {
                return wrapper;
            }
        }
        return null;
    }
}
