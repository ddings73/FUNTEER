package com.yam.funteer.live;

import com.yam.funteer.user.entity.User;

import io.openvidu.java.client.OpenViduRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
	private User user;
	private OpenViduRole role;
}
