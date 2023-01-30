package com.a608.modac.model.room;

import lombok.Getter;
import com.a608.modac.model.user.User;

@Getter
public class RoomRequest {
	private String title;  		//
	private String description;	//
	private Integer maxSize;
	private String multiTheme;	//
	private Integer publicType;
	private Long usersSeq;

	public Room toEntity(User user, String invitationCode){
		return Room.builder()
			.title(title)
			.description(description)
			.maxSize(maxSize)
			.multiTheme(multiTheme)
			.publicType(publicType)
			.invitationCode(invitationCode)
			.user(user)
			.build();
	}
}
