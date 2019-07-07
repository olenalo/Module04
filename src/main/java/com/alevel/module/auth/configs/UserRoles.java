package com.alevel.module.auth.configs;

public enum UserRoles {

    ROLE_USER("role_user"),  // end user
    ROLE_ADMIN("role_admin");  // admin user

    private String shortTitle;

    UserRoles (String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @Override
    public String toString() {
        return shortTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }
}
