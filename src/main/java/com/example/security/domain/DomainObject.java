package com.example.security.domain;


import com.example.security.utils.GuidGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Lei Tao
 */
@MappedSuperclass
public abstract class DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version", length = 11)
    private int version = 0;


    @Column(name = "guid")
    private String guid = GuidGenerator.createGuid();


    @Column(name = "archived", length = 1)
    protected boolean archived = false;

    @Column(name = "created_datetime")
    protected LocalDateTime createdDateTime = LocalDateTime.now();


    @Column(name = "last_modified_datetime")
    private LocalDateTime lastModifiedDateTime;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        lastModifiedDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DomainObject)) {
            return false;
        }

        DomainObject other = (DomainObject) obj;

        // if the id is missing, return false
        if (getGuid() == null) {
            return false;
        }

        // equivalence by guid
        return getGuid().equals(other.getGuid());
    }

    public boolean isArchived() {
        return archived;
    }

    public void archived() {
        this.archived = true;
    }

    public void unarchived() {
        this.archived = false;
    }

    public boolean isCreate() {
        return id == null;
    }
}
