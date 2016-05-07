/*
 * IP Monitor is a simple application which monitors your public IP
 * address for changes and lets you set different kinds of notification
 * such as email, audio, pop up or executing a command. It can also run
 * in background as a Windows service or Linux/Mac daemon.
 *
 * Copyright (C) 2007 - 2016  Gabriel Zanetti http://github.com/pupi1985
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package controller.extras;

import java.awt.TrayIcon.MessageType;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MessageTypeModel extends AbstractListModel<MessageTypeWrapper>
        implements ComboBoxModel<MessageTypeWrapper> {

    private List<MessageTypeWrapper> messageTypeWrappers;
    private MessageTypeWrapper selectedItem;

    public MessageTypeModel() {
        messageTypeWrappers = Arrays.asList(
            new MessageTypeWrapper("None", MessageType.NONE),
            new MessageTypeWrapper("Information", MessageType.INFO),
            new MessageTypeWrapper("Warning", MessageType.WARNING),
            new MessageTypeWrapper("Error", MessageType.ERROR)
        );
    }

    public MessageTypeWrapper getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Object item) {
        selectedItem = (MessageTypeWrapper) item;
    }

    public MessageTypeWrapper getElementAt(int index) {
        try {
            return messageTypeWrappers.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    public int getSize() {
        return messageTypeWrappers.size();
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
