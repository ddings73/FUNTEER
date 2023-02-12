package com.yam.funteer.live.repository;

import com.yam.funteer.live.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Long> {
}
