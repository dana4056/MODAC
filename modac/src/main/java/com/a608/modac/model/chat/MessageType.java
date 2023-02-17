package com.a608.modac.model.chat;

public enum MessageType {
	ENTER("ENTER"), TALK("TALK"), LEAVE("LEAVE");

	private final String type;

	MessageType(final String type) {
		this.type = type;
	}

	public String type(){
		return type;
	}

}
