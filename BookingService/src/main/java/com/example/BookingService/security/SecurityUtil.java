package com.example.BookingService.security;

public class SecurityUtil {

    public static String getCurrentUserRole() {
        return "ADMIN"; // later replace USER OR with JWT
    }
}
