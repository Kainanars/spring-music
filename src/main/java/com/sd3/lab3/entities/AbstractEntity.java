package com.sd3.lab3.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    public AbstractEntity(){
        Date now = new Date();
        CreatedAt = now;
        UpdatedAt = now;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IX_ID")
    private long Id;
    @Column(name = "DT_CREATED_AT")
    private final Date CreatedAt;
    @Column(name = "DT_UPDATED_AT")
    private Date UpdatedAt;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
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

}
