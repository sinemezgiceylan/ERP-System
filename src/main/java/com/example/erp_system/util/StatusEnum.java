package com.example.erp_system.util;

public enum StatusEnum {
    APPROVED,
    WAITING,
    REJECTED;

    public static StatusEnum fromString(String status) {
        return switch (status) {
            case "approved" -> APPROVED;
            case "waiting" -> WAITING;
            case "rejected" -> REJECTED;
            default -> null;
        };
    }
}
