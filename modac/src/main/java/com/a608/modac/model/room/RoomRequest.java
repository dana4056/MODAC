package com.a608.modac.model.room;

import lombok.Getter;
import com.a608.modac.model.user.User;

public class RoomRequest {
	@Getter
	public static class CreateRoomRequest{
		private String title;
		private String description;
		private Integer maxSize;
		private String multiTheme;
		private Integer publicType;
		private String invitationCode;
		private Long userSeq;

		public Room toEntity(User user){
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

	@Getter
	public static class UpdateRoomRequest{
		private Long seq;
		private String title;
		private String description;
		private String multiTheme;

		public Room toEntity(){
			return Room.builder()
				.seq(seq)
				.title(title)
				.description(description)
				.multiTheme(multiTheme)
				.build();
		}
	}

}
