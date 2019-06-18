package com.wiley.internal.apps.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSearchDTO {

    private String userName;
    private String designation;
    private Date joinDate;
    private List<UserSkillDTO> userSkillDTOList = new ArrayList<>();

    public UserSearchDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<UserSkillDTO> getUserSkillDTOList() {
        return userSkillDTOList;
    }

    public void setUserSkillDTOList(List<UserSkillDTO> userSkillDTOList) {
        this.userSkillDTOList = userSkillDTOList;
    }
}
