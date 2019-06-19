package com.wiley.internal.apps.dto;

public class SkillDTO {

    private double experiace;
    private long skillId;
    private long skillLevelId;
    private int  starRate;

    public SkillDTO() {
    }

    public double getExperiace() {
        return experiace;
    }

    public void setExperiace(double experiace) {
        this.experiace = experiace;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public long getSkillLevelId() {
        return skillLevelId;
    }

    public void setSkillLevelId(long skillLevelId) {
        this.skillLevelId = skillLevelId;
    }

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }
}
