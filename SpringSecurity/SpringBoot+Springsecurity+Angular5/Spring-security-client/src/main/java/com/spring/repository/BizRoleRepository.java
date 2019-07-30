package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.BizRole;

@Repository
public interface BizRoleRepository extends JpaRepository<BizRole, Long> {
	
	/**
     * 根据id查找角色
     *
     * @param id ID
     * @return
     */
	Optional<BizRole> findById(Long id);

    /**
     * 根据名称查找角色
     *
     * @param name 名称
     * @return
     */
	BizRole findByName(String name);


}
