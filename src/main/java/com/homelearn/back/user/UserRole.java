package com.homelearn.back.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN","관리자"),
    DDUBEOKY("DDUBEOKY","일반 사용자");

    private final String role;
    private final String roleKR;
    private final String authRole;

    UserRole(String role, String roleKR) {
        this.role=role;
        this.roleKR = roleKR;
        this.authRole="ROLE_"+role;
    }

}
