package com.wiley.internal.apps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wiley.internal.apps.domain.UserSkill;
import com.wiley.internal.apps.dto.UserSearchDTO;
import com.wiley.internal.apps.dto.UserSkillDTO;
import com.wiley.internal.apps.repo.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wiley.internal.apps.domain.User;
import com.wiley.internal.apps.exception.UserNotFoundException;
import com.wiley.internal.apps.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	private UserSkillRepository  userSkillRepository;

	@Autowired
	public UserServiceImpl(final UserRepository userRepository, final  UserSkillRepository userSkillRepository) {
		this.userRepository = userRepository;
		this.userSkillRepository = userSkillRepository;
	}

	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<User> retrieveAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public void deleteUser(String userName) {
		 this.userRepository.deleteById(userName);
	}

	@Override
	public User findUser(String userName) {
		
	Optional<User> userOprtional = this.userRepository.findById(userName);
		
		if (!userOprtional.isPresent()) {
			throw new UserNotFoundException("User does not exsists :: " + userName);
		}
		
		return userOprtional.get();
	}

	@Override
	public List<User> findByUserNameIgnoreCaseContaining(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserNameIgnoreCaseContaining(userName);
	}

	@Override
	public ResponseEntity<?> getAllUserDetails () {
		List<User> userList = userRepository.findAll();
		List<UserSearchDTO> userSearchDTOList = new ArrayList<>();
		if(userList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			for(User user : userList) {
				List<UserSkill> userSkillList = userSkillRepository.findByUser(user);

				UserSearchDTO userSearchDTO = new UserSearchDTO();
				userSearchDTO.setUserName(user.getUserName());
				userSearchDTO.setDesignation(user.getDesignation());
				userSearchDTO.setJoinDate(user.getCreatedDate());

				if(userSkillList.isEmpty()){
					userSearchDTOList.add(userSearchDTO);
				} else {

					List<UserSkillDTO> userSkillDTOList = new ArrayList<>();

					for(UserSkill userSkill : userSkillList) {
						UserSkillDTO userSkillDTO = new UserSkillDTO();
						userSkillDTO.setSkillId(userSkill.getId());
						userSkillDTO.setSkillname(userSkill.getSkill().getName());
						userSkillDTO.setSkillDescription(userSkill.getSkillLevel().getDescription());
						userSkillDTO.setSkillValue(userSkill.getSkillLevel().getValue());
						userSkillDTO.setSkillValue(userSkill.getSkillLevel().getValue());
						userSkillDTO.setExperiace(userSkill.getExperience());

						userSkillDTOList.add(userSkillDTO);
					}

					userSearchDTO.setUserSkillDTOList(userSkillDTOList);
					userSearchDTOList.add(userSearchDTO);
				}
			}
			return new ResponseEntity<>(userSearchDTOList,HttpStatus.OK);
		}
	}

}
