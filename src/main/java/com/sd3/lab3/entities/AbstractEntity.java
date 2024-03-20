package com.sd3.lab3.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    public AbstractEntity(){
        Date now = new Date();
        createdAt = now;
        updatedAt = now;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_gen")
    @SequenceGenerator(name = "entity_seq_gen", sequenceName = "entity_seq", allocationSize = 1)
    @Column(name = "IX_ID")
    private long id;
    @Column(name = "DT_CREATED_AT")
    private final Date createdAt;
    @Column(name = "DT_UPDATED_AT")
    private Date updatedAt;


    public long getId() {
        return id;
    }

    public void setId(long idPass) {
        id = idPass;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAte) {
        updatedAt = updatedAte;
    }

}
