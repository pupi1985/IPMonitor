/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import java.awt.TrayIcon.MessageType;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MessageTypeModel extends AbstractListModel<MessageTypeWrapper>
		implements ComboBoxModel<MessageTypeWrapper> {

	private MessageTypeWrapper[] messageTypeWrappers;
	private MessageTypeWrapper selectedItem;

	public MessageTypeModel() {
		messageTypeWrappers = new MessageTypeWrapper[] {
				new MessageTypeWrapper("None", MessageType.NONE),
				new MessageTypeWrapper("Information", MessageType.INFO),
				new MessageTypeWrapper("Warning", MessageType.WARNING),
				new MessageTypeWrapper("Error", MessageType.ERROR)
		};
	}

	public MessageTypeWrapper getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Object item) {
		selectedItem = (MessageTypeWrapper) item;
	}

	public MessageTypeWrapper getElementAt(int index) {
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
