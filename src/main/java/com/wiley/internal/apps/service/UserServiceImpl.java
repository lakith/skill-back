package com.wiley.internal.apps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wiley.internal.apps.domain.Skill;
import com.wiley.internal.apps.domain.SkillLevel;
import com.wiley.internal.apps.domain.UserSkill;
import com.wiley.internal.apps.dto.*;
import com.wiley.internal.apps.repo.SkillLevelRepository;
import com.wiley.internal.apps.repo.SkillRepository;
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

	private SkillLevelRepository skillLevelRepository;

	private SkillRepository skillRepository;

	@Autowired
	public UserServiceImpl(final UserRepository userRepository, final  UserSkillRepository userSkillRepository, final  SkillLevelRepository skillLevelRepository, final SkillRepository skillRepository) {
		this.userRepository = userRepository;
		this.userSkillRepository = userSkillRepository;
		this.skillLevelRepository = skillLevelRepository;
		this.skillRepository = skillRepository;
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
						userSkillDTO.setSkillValue(userSkill.getStarRate());
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

	@Override
	public ResponseEntity<?> saveUserSkills(UserSkillSaveDTO userSkillSaveDTO) {
		Optional<User> optionalUser = userRepository.findById(userSkillSaveDTO.getUsername());

		if(!optionalUser.isPresent()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			for(SkillDTO skillDTO : userSkillSaveDTO.getSkillDTOList()){
				Optional<SkillLevel> optionalSkillLevel = skillLevelRepository.findById(skillDTO.getSkillLevelId());
				if(!optionalSkillLevel.isPresent()){
					ResponseDTO responseDTO = new ResponseDTO();
					responseDTO.setMessage("Invalid Data Present - Roll Back");
					responseDTO.setStatus(false);
					return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
				}
				Optional<Skill> optionalSkill = skillRepository.findById(skillDTO.getSkillId());
				if(!optionalSkill.isPresent()){
					ResponseDTO responseDTO = new ResponseDTO();
					responseDTO.setMessage("Invalid Data Present - Roll Back");
					responseDTO.setStatus(false);
					return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
				}

				UserSkill userSkill = new UserSkill();
				userSkill.setUser(optionalUser.get());
				userSkill.setSkill(optionalSkill.get());
				userSkill.setSkillLevel(optionalSkillLevel.get());
				userSkill.setExperience((float) skillDTO.getExperiace());
				userSkill.setStarRate(skillDTO.getStarRate());

				userSkillRepository.save(userSkill);
			}

			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setMessage("Skills Saved Successfully");
			responseDTO.setStatus(true);

			return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);

		}
	}

}
