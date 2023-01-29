package com.yam.funteer.user.repository;

import com.yam.funteer.user.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
}
