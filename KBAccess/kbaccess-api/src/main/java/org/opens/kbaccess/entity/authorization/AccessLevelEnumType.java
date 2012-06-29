/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.authorization;


/**
 *
 * @author amchaar
 */
public enum AccessLevelEnumType {

    ADMINISTRATOR("admin"),
    MODERATOR("moder"),
    CONTRIBUTOR("contrib"),
    ANONYME("anony");

    private final String type;

    /**
     * 
     * @return The access level as a string
     */
    public String getType() {
        return type;
    }

    private AccessLevelEnumType(String type) {
        this.type = type;
    }

    /**
     * Convert a string value to its corresponding acces level.
     * 
     * If the string value does not match with any access level, ANONYME is
     * returned.
     * 
     * @param type The string value to match with an access level
     * @return The access level corresponding with the given argument or
     *         ANONYME is the corresponding acces level is not found.
     */
    public static AccessLevelEnumType getEnumFromString(String type) {
        for (AccessLevelEnumType member : values()) {
            if (member.getType().equalsIgnoreCase(type)) {
                return member;
            }
        }
        return AccessLevelEnumType.ANONYME;
    }
}
