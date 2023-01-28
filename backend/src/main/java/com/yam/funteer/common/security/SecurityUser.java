package com.yam.funteer.common.security;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class SecurityUser extends User {

    com.yam.funteer.user.entity.User user;
    public SecurityUser(com.yam.funteer.user.entity.User user){
        super(user.getName(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getUserType().getAuthority())));
        this.user = user;
    }
}
