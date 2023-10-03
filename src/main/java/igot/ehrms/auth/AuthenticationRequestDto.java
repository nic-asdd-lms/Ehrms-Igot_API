package igot.ehrms.auth;

import java.io.Serializable;

public class AuthenticationRequestDto implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String id;
    private String password;

    //need default constructor for JSON Parsing
    public AuthenticationRequestDto() {

    }

    public AuthenticationRequestDto(String username, String password) {
        this.setId(username);
        this.setPassword(password);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String username) {
        this.id = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}