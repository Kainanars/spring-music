package com.sd3.market.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    public AbstractEntity(){
        Date now = new Date();
        CreatedAt = now;
        UpdatedAt = now;
        Active = true;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID Id;
    @Column(name = "DT_CREATED_AT")
    private final Date CreatedAt;
    @Column(name = "DT_UPDATED_AT")
    private Date UpdatedAt;
    @Column(name = "ST_ACTIVE")
    private Boolean Active;


    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        UpdatedAt = updatedAt;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
