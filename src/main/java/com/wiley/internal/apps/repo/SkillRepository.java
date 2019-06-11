package com.wiley.internal.apps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiley.internal.apps.domain.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> { 
	
	List<Skill> findByNameContaining(String name);
}
