package com.yam.funteer.user.entity;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.UserType;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "team")
@Getter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends User{
    private String description;
    @ManyToOne
    @JoinColumn(name = "team_banner")
    private Attach banner;

    private Long totalFundingAmount;

    public Optional<Attach> getBanner(){
        return Optional.ofNullable(banner);
    }
    public void signOut(){
        super.signOut(UserType.TEAM_RESIGN);
    }

    public void update(Attach profile, Attach banner, String description) {
        super.updateProfile(profile);
        this.banner = banner;
        this.description = description;
    }

    public void addTotalFundingAmount(Long amount) {
        this.totalFundingAmount += amount;
    }

    @Override
    public boolean equals(Object obj) {
        return getId() == ((User)obj).getId();
    }
}
