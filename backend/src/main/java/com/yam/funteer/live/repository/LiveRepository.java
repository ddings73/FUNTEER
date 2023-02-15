package com.yam.funteer.live.repository;

import com.yam.funteer.live.entity.Live;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LiveRepository extends JpaRepository<Live, Long> {
    Optional<Live> findByFundingTeamNameAndEndTimeIsNull(String name);
    Page<Live> findByEndTimeIsNull(Pageable pageable);
}
