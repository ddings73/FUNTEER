package com.yam.funteer.donation.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation,Long> {
	List<Donation>findAllByOrderByDonationIdDesc(Pageable pageable);
	List <Donation> findAllByPostTypeOrderByDonationIdDesc(PostType postType,Pageable pageable);
	Optional<Donation> findFirstByOrderByDonationIdDesc();
	Optional<Donation> findByDonationId(Long donationId);
}
