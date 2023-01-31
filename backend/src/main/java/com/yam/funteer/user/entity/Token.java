package com.yam.funteer.user.entity;

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

    public void update(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
