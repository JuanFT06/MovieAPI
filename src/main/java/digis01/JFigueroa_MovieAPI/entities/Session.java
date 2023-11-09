/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digis01.JFigueroa_MovieAPI.entities;

/**
 *
 * @author digis
 */
public class Session {
    private boolean success;
    private String session_id;
    private String request_token;

    public Session() {
    }

    public Session(boolean success, String session_id, String request_token) {
        this.success = success;
        this.session_id = session_id;
        this.request_token = request_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
    
    
}
