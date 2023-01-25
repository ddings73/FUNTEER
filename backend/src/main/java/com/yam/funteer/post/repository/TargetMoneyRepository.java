package com.yam.funteer.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.TargetMoney;
import com.yam.funteer.post.entity.TargetMoneyID;

public interface TargetMoneyRepository extends JpaRepository<TargetMoney, TargetMoneyID> {
}
