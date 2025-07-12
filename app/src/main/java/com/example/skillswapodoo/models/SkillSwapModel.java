package com.example.skillswapodoo.models;

public class SkillSwapModel {
    private String profileName;
    private String skillsOffered;
    private String skillsWanted;
    private float rating;
    private int profileImageResId;

    public SkillSwapModel(String profileName, String skillsOffered, String skillsWanted, float rating, int profileImageResId) {
        this.profileName = profileName;
        this.skillsOffered = skillsOffered;
        this.skillsWanted = skillsWanted;
        this.rating = rating;
        this.profileImageResId = profileImageResId;
    }

    public String getProfileName() { return profileName; }
    public String getSkillsOffered() { return skillsOffered; }
    public String getSkillsWanted() { return skillsWanted; }
    public float getRating() { return rating; }
    public int getProfileImageResId() { return profileImageResId; }
}
