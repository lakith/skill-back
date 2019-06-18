package com.wiley.internal.apps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wiley.internal.apps.domain.SkillLevel;
import com.wiley.internal.apps.domain.UserSkill;
import com.wiley.internal.apps.dto.StatusDTO;
import com.wiley.internal.apps.repo.SkillLevelRepository;
import com.wiley.internal.apps.repo.UserRepository;
import com.wiley.internal.apps.repo.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wiley.internal.apps.domain.Skill;
import com.wiley.internal.apps.repo.SkillRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {


    private SkillRepository skillRepository;
    private UserSkillRepository userSkillRepository;
    private UserRepository userRepository;
    private SkillLevelRepository skillLevelRepository;

    @Autowired
    public SkillServiceImpl(final SkillRepository skillRepository, final UserSkillRepository userSkillRepository, final UserRepository userRepository,final SkillLevelRepository skillLevelRepository) {
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.userRepository = userRepository;
        this.skillLevelRepository = skillLevelRepository;
    }

    @Override
    public ResponseEntity<?> createSkill(Skill skill) {
        int userCount = 0;
        int devCount = 0;
        double percentage = 0.0;
        try {
            skill =  this.skillRepository.save(skill);
            userCount = userRepository.getUserCount();
            devCount = userSkillRepository.getDevCount(skill.getId());
            percentage = ( Double.valueOf(devCount)/Double.valueOf(userCount) ) * 100;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setSkillName(skill.getName());
        statusDTO.setEngineers(devCount);
        statusDTO.setRate(percentage);

        return new ResponseEntity<>(statusDTO,HttpStatus.CREATED);
    }

    @Override
    public List<Skill> retrieveAllSkills() {
        return this.skillRepository.findAll();
    }

    @Override
    public void deleteSkill(Long skillId) {
        this.skillRepository.deleteById(skillId);
    }

    public List<Skill> findSkillByName(String skillName) {
        return this.skillRepository.findByNameContaining(skillName);
    }

    @Override
    public Skill findById(Long id) {
        // TODO Auto-generated method stub
        return skillRepository.findById(id).get();
    }


    /***
     * @ author  Lakith Muthugala.
     * @ version 1.0
     * @ since 2019-06-14
     * @ Work - Status Service.
     ***/

    @Override
    public ResponseEntity<?> getSkillsWithDevolopers() {

        List<StatusDTO> statusDTOList = new ArrayList<>();
        List<Skill> skillList = new ArrayList<>();
        int userCount;
        try {
            skillList = skillRepository.findAll();
            userCount = userRepository.getUserCount();

        } catch (Exception e) {
            throw new RuntimeException("Something Went Wrong");
        }

        if (skillList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Skill skill : skillList) {
                try {

                    int devCount = userSkillRepository.getDevCount(skill.getId());
                    double percentage = ( Double.valueOf(devCount)/Double.valueOf(userCount) ) * 100;

                    StatusDTO statusDTO = new StatusDTO();
                    statusDTO.setEngineers(devCount);
                    statusDTO.setRate(percentage);
                    statusDTO.setSkillName(skill.getName());

                    statusDTOList.add(statusDTO);
                } catch (Exception e) {
                    throw new RuntimeException("Something Went Wrong");
                }
            }
            return new ResponseEntity<>(statusDTOList, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity getAllSkillLevels() {
        List<SkillLevel> skillLevels = skillLevelRepository.findAll();
        if(skillLevels.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(skillLevels,HttpStatus.OK);
        }
    }
}
