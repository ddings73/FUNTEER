package com.yam.funteer.donation.Entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DonationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "donation_id")
    private Integer id;

    @Column(name="donation_amount")
    private Integer amount;

    @Column(name="donation_date")
    private LocalDateTime date;
}
