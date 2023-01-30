package com.yam.funteer.user.entity;

import com.yam.funteer.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity @Builder
@ToString @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    private Long id;
    private String refreshToken;

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
