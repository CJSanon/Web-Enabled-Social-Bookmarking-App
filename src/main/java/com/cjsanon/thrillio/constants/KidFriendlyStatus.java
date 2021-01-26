package com.cjsanon.thrillio.constants;

public enum KidFriendlyStatus {
    APPROVED("approved"),
    REJECTED("rejected"),
    UNKNOWN("unknown");

    KidFriendlyStatus(String name) {
        this.name = name;
    }
    private final String name;

    public String getName() {
        return name;
    }
}
