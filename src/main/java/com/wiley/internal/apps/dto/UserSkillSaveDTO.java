package com.wiley.internal.apps.dto;

import java.util.ArrayList;
import java.util.List;

public class UserSkillSaveDTO {

    private String username;
    private List<SkillDTO> skillDTOList = new ArrayList<>();

    public UserSkillSaveDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SkillDTO> getSkillDTOList() {
        return skillDTOList;
    }

    public void setSkillDTOList(List<SkillDTO> skillDTOList) {
        this.skillDTOList = skillDTOList;
    }
}
