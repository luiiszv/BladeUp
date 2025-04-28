package com.bladeUp.bladeUp.auth;

public class LoginDTO {
    private String correo;
    private String password;

    // Constructor vacío
    public LoginDTO() {
    }

    // Constructor completo
    public LoginDTO(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
