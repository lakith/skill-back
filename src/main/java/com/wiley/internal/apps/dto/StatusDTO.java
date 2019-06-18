package com.wiley.internal.apps.dto;

import java.util.stream.Stream;

public class StatusDTO {

    private String skillName;
    private int Engineers;
    private double rate;


    public StatusDTO() {
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getEngineers() {
        return Engineers;
    }

    public void setEngineers(int engineers) {
        Engineers = engineers;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
