package igot.integration.user;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ehrms_users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String password;
    private String org;
    private LocalDateTime created_on;


    
    public UserModel() {
    }

    public UserModel(String org, String password, LocalDateTime createdOn){
        this.setOrg(org);
        this.setPassword(password);
        this.setCreated_on(createdOn);

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

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }

}
