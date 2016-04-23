/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import java.awt.TrayIcon.*;

public class MessageTypeWrapper {

	private String name;

	private MessageType messageType;

	public MessageTypeWrapper(String name, MessageType messageType) {
		this.name = name;
		this.messageType = messageType;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public String toString() {
		return name;
	}

}
