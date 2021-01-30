package com.cjsanon.thrillio.entities;

import com.cjsanon.thrillio.constants.KidFriendlyStatus;

public abstract class Bookmark {
    private long id;
    private String title;
    private String profileUrl;
    private KidFriendlyStatus kidFriendlyStatus = KidFriendlyStatus.UNKNOWN; //unknown, approve, or reject by editor
    private User kidFriendlyMarkedBy;
    private User sharedBy;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public KidFriendlyStatus getKidFriendlyStatus() {
        return kidFriendlyStatus;
    }
    public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatus) {
        this.kidFriendlyStatus = kidFriendlyStatus;
    }

    public User getKidFriendlyMarkedBy() {
        return kidFriendlyMarkedBy;
    }
    public void setKidFriendlyMarkedBy(User kidFriendlyMarkedBy) {
        this.kidFriendlyMarkedBy = kidFriendlyMarkedBy;
    }

    public User getSharedBy() {
        return sharedBy;
    }
    public void setSharedBy(User sharedBy) {
        this.sharedBy = sharedBy;
    }

    public abstract boolean isKidFriendlyEligible();
}