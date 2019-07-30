package com.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.BizUser;

@Repository
public interface BizUserRepository extends JpaRepository<BizUser, Long> {

	BizUser findByUsername(String username);
}
