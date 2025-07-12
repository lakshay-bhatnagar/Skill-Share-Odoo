package com.example.skillswapodoo.models;

public class SwapRequestModel {
    private String name;
    private String skillsOffered;
    private String skillsWanted;
    private float rating;
    private int profileImageResId;
    private String status; // "Pending", "Rejected", "Accepted"

    public SwapRequestModel(String name, String skillsOffered, String skillsWanted, float rating,
                            int profileImageResId, String status) {
        this.name = name;
        this.skillsOffered = skillsOffered;
        this.skillsWanted = skillsWanted;
        this.rating = rating;
        this.profileImageResId = profileImageResId;
        this.status = status;
    }

    // Getters
    public String getName() { return name; }
    public String getSkillsOffered() { return skillsOffered; }
    public String getSkillsWanted() { return skillsWanted; }
    public float getRating() { return rating; }
    public int getProfileImageResId() { return profileImageResId; }
    public String getStatus() { return status; }

    // Setter for status to update later
    public void setStatus(String status) { this.status = status; }
}

