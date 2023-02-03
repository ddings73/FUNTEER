package com.yam.funteer.attach.repository;

import java.util.List;

import com.yam.funteer.attach.entity.Attach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachRepository extends JpaRepository<Attach, Long> {
	Attach findByPathEndingWith(String fileName);
}
