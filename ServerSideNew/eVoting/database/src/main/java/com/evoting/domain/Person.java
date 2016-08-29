package com.evoting.domain;

import org.hibernate.envers.Audited;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

/**
 * Created by Azhar on 2016/07/14.

 */



@Audited
@Entity
@EnableTransactionManagement
@Table(name = "person")

public class Person {
    private int id;
    private Set<Permission> permissions = new HashSet<Permission>(0);
    private UserType userType;
    private String idNum;
    private String password;
    private String name;
    private String surname;
    private String locationRegistered;
    private String cellphone;
    private String email;
    private int votes = 0;
    private boolean votedNationalElection = false;
    private boolean votedProvincialElection = false;
    private boolean active = false;

    public Person() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "id_num", unique = true, nullable = false, length = 13)
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Column(name = "password", unique = false, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_permission",
            joinColumns = { @JoinColumn(name = "permissions") },
            inverseJoinColumns = { @JoinColumn(name = "persons")} )
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @ManyToOne
    @JoinTable(name = "person_user_type")
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Column(name = "name", unique = false, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", unique = false, nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "location_registered", unique = false, nullable = false)
    public String getLocationRegistered() {
        return locationRegistered;
    }

    public void setLocationRegistered(String locationRegistered) {
        this.locationRegistered = locationRegistered;
    }

    @Column(name = "cellphone", unique = false, nullable = false)
    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Column(name = "email", unique = true, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "votes", unique = false, nullable = true)
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Column(name = "voted_national_election", unique = false, nullable = false)
    public boolean isVotedNationalElection() {
        return votedNationalElection;
    }

    public void setVotedNationalElection(boolean votedNationalElection) {
        this.votedNationalElection = votedNationalElection;
    }

    @Column(name = "voted_provincial_election", unique = false, nullable = false)
    public boolean isVotedProvincialElection() {
        return votedProvincialElection;
    }

    public void setVotedProvincialElection(boolean votedProvincialElection) {
        this.votedProvincialElection = votedProvincialElection;
    }

    @Column(name = "active", unique = false, nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}