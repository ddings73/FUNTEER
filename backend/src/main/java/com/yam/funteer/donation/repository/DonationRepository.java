package com.yam.funteer.donation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.post.entity.Post;

public interface DonationRepository extends JpaRepository<Donation,Long> {
	Optional<Donation> findById(Long id);
}
