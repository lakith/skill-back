package com.wiley.internal.apps.service;

import java.util.List;

import com.wiley.internal.apps.domain.User;
import com.wiley.internal.apps.dto.UserSkillSaveDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

	User createUser(User user);
	
	List<User> retrieveAllUsers();
	
	void deleteUser(String userName);
	
	User findUser(String userName);
	
	List<User> findByUserNameIgnoreCaseContaining(String userName);

	ResponseEntity<?> getAllUserDetails ();

	ResponseEntity<?> saveUserSkills(UserSkillSaveDTO userSkillSaveDTO);
}
