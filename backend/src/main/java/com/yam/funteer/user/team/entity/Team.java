package com.yam.funteer.user.team.entity;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.user.UserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Table(name = "team")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    @Column(name = "team_name")
    private String name;
    @Column(name = "team_description")
    private String discription;
    @Email
    @Column(name = "team_email", unique = true)
    private String email;
    @Column(name = "team_password")
    private String password;
    @Column(name = "team_phone")
    private String phone;
    @ManyToOne @JoinColumn(name = "team_profile")
    private Attach profileImg;

    @ManyToOne @JoinColumn(name = "team_banner")
    private Attach banner;
    @Column(name = "team_money")
    private Long money;
    @Column(name = "team_status", nullable = false)
    private UserType status;
    @Column(name = "team_regdate")
    private LocalDateTime regDate;

}
