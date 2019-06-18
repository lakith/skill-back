package com.wiley.internal.apps.service;

import java.util.List;

import com.wiley.internal.apps.domain.Skill;
import com.wiley.internal.apps.domain.UserSkill;
import org.springframework.http.ResponseEntity;

public interface SkillService {

	ResponseEntity<?> createSkill(Skill skill);
	
	void deleteSkill(Long skillId);
	
	List<Skill> findSkillByName(String skillName);
	
	List<Skill> retrieveAllSkills();
	
	Skill findById(Long id);

	/***
	 * @ author - Lakith Muthugala.
	 * @ version 1.0
	 * @ since 2019-06-14
	 * @ Work - Status Service.
	 ***/

	ResponseEntity<?> getSkillsWithDevolopers();

	ResponseEntity getAllSkillLevels();
}
