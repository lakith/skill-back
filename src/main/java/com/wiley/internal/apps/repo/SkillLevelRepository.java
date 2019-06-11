package com.wiley.internal.apps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiley.internal.apps.domain.SkillLevel;

public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long> { }
