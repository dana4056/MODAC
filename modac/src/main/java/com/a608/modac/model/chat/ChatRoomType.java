package com.a608.modac.model.chat;

public enum ChatRoomType {
	GROUP("GROUP"), DIRECT("DIRECT");

	private final String type;

	ChatRoomType(final String type) {
		this.type = type;
	}

	public String type(){
		return type;
	}

}

