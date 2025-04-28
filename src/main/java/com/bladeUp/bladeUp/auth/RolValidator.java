package com.bladeUp.bladeUp.auth;

import jakarta.servlet.http.HttpServletRequest;

public class RolValidator {

    public static boolean tieneRol(HttpServletRequest request, String rolEsperado) {
        String rol = (String) request.getAttribute("rol");
        return rol != null && rol.equalsIgnoreCase(rolEsperado);
    }
}