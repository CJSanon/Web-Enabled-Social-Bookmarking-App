package com.cjsanon.thrillio.constants;

public enum UserType {
    USER("user"),
    EDITOR("editor"),
    CHIEF_EDITOR("chiefeditor");

    UserType(String name){
        this.name = name;
    }
    private final String name;
    public String getName() {
        return name;
    }
}
