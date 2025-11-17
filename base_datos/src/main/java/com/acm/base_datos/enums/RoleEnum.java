package com.acm.base_datos.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER"),
    MODERATOR("MODERATOR");
    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }
}
