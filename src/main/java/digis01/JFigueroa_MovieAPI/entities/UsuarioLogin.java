/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digis01.JFigueroa_MovieAPI.entities;

import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author digis
 */
public class UsuarioLogin {
    @NotEmpty(message="ingresa un username")
    private String username;
    @NotEmpty(message="ingresa una contraseña")
    private String password;
    private String request_token;

    public UsuarioLogin() {
    }

    public UsuarioLogin(String username, String password, String request_token) {
        this.username = username;
        this.password = password;
        this.request_token = request_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
    
    
}
