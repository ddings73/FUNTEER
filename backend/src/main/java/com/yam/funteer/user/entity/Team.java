package com.yam.funteer.user.entity;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.user.UserType;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "team")
@Getter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends User{
    private String discription;
    @ManyToOne
    @JoinColumn(name = "team_banner")
    private Attach banner;
    public void signOut(){
        super.signOut(UserType.TEAM_RESIGN);
    }
}
