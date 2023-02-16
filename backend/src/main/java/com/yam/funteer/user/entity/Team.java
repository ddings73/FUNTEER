package com.yam.funteer.user.entity;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.UserType;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private LocalDateTime lastActivity;

    public Optional<Attach> getBanner(){
        return Optional.ofNullable(banner);
    }
    public void signOut(){
        super.signOut(UserType.TEAM_RESIGN);
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateBanner(Attach banner){
        this.banner = banner;
    }

    public void addTotalFundingAmount(Long amount) {
        this.totalFundingAmount += amount;
    }

    @Override
    public boolean equals(Object obj) {
        return getId() == ((User)obj).getId();
    }

    public void accept() {
        updateLastActivity();
        super.teamAccept();
    }

    public void updateLastActivity(){
        this.lastActivity = LocalDateTime.now();
    }

    public boolean expiredCheck() {
        if(this.lastActivity != null) {
            long between = ChronoUnit.YEARS.between(this.lastActivity, LocalDateTime.now());
            if (between >= 1) {
                super.expire();
                return true;
            }
        }
        return false;
    }

    public void expire(){
        super.expire();
    }
}
