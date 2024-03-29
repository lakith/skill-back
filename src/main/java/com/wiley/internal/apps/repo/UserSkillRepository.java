package com.wiley.internal.apps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiley.internal.apps.domain.User;
import java.util.Optional;

import com.wiley.internal.apps.domain.Skill;
import com.wiley.internal.apps.domain.SkillLevel;
import com.wiley.internal.apps.domain.UserSkill;
import org.springframework.data.jpa.repository.Query;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> { 
	
	List<UserSkill> findByUser(User user);

	Optional<UserSkill> findBySkillAndSkillLevel(Skill skill, SkillLevel skillLevel);

	@Query("SELECT count (u.id) FROM UserSkill u WHERE u.skill.id =?1")
	int getDevCount(long skillId);
}
