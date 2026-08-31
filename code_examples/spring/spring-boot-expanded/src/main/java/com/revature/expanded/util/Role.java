package com.revature.expanded.util;

/*
 * We have named our Enums with the pattern:
 *      "ROLE_<ROLE_LEVEL>"
 * because Spring Security is expecting the
 * "ROLE_" naming convention
 */
public enum Role {
    ROLE_ADMIN,
    ROLE_EMPLOYEE,
    ROLE_MANAGER
}
