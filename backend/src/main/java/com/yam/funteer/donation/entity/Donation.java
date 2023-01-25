package com.yam.funteer.donation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="post")
public class Donation {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="post_title")
    private String title;

    @Column(name="post_content")
    private String content;

    @Column(name="post_date")
    private LocalDateTime date;

    @Column(name="post_hit")
    private Long hit;

    @Column(name="post_thumbnail")
    private String thumbnail;

    @Column(name="post_password")
    private String password;

    @Column(name="post_start")
    private LocalDateTime start;

    @Column(name="post_end")
    private LocalDateTime end;

    @Enumerated
    @Column(name="code")
    private
}
