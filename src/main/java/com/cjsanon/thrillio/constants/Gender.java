package com.cjsanon.thrillio.constants;

public enum Gender {
    MALE(0),
    FEMALE(1),
    TRANSGENDER(2);

    Gender(int value){
        this.value = value;
    }
    private final int value;
    public int getValue() {
        return value;
    }
}
