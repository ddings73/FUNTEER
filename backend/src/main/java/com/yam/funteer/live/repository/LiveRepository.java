package com.yam.funteer.live.repository;

import com.yam.funteer.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LiveRepository extends JpaRepository<Live, Long> {
    Optional<Live> findBySessionId(String sessionId);

}
