package com.yam.funteer.donation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation,Long> {
	List<Donation>findAllByOrderByDonationId(Pageable pageable);
	Optional<Donation> findFirstByOrderByDonationIdDesc();
	Optional<Donation> findByDonationId(Long donationId);
}
