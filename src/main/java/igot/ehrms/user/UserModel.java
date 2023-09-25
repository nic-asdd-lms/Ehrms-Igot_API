package igot.ehrms.user;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ehrms_users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String password;
    private String org;


    public UserModel() {
    }

    public UserModel(String org, String password){
        this.setOrg(org);
        this.setPassword(password);
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrg() {
    return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    
}
