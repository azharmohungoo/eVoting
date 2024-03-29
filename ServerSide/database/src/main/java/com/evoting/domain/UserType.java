package com.evoting.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Audited
@Table(name="user_type")
public class UserType {
    private int id;
    private String userType;
    private Set<Person> persons = new HashSet<Person>(0);

    public UserType() {
    }

    public UserType(int id, String userType) {
        this.id = id;
        this.userType = userType;
    }

    public UserType(String userType) {
        this.userType = userType;
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

    @Column(name = "user_type", nullable = false)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL)
    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                ", persons=" + persons +
                '}';
    }
}
