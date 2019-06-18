package com.wiley.internal.apps.dto;

public class UserSkillDTO {

    private long skillId;
    private String skillname;
    private Integer skillValue;
    private String skillDescription;
    private float experiace;

    public UserSkillDTO() {
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public Integer getSkillValue() {
        return skillValue;
    }

    public void setSkillValue(Integer skillValue) {
        this.skillValue = skillValue;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public float getExperiace() {
        return experiace;
    }

    public void setExperiace(float experiace) {
        this.experiace = experiace;
    }
}
