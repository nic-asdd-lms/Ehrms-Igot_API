package igot.ehrms.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "ehrms_log")
public class LogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgId;
    private LocalDateTime timestamp;

    public LogModel(String orgId, LocalDateTime timestamp){
        this.setOrgId(orgId) ;
        this.setTimestamp(timestamp);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
