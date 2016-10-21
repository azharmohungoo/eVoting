package com.evoting.domain;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Azhar on 2016/07/17.
 */

@Entity
@RevisionEntity(RevListener.class)
public class RevEntity extends DefaultRevisionEntity {
    private String userType;
    //private String username; TODO
    private String action;

    public RevEntity() {
    }

    @Column(name = "user_type", nullable = false)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /*@Column(name = "username", nullable = false)
    public String getUsername() { return username; }
    TODO
    public void setUsername(String username) { this.username = username; }*/

    @Column(name = "action", nullable = false)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
