package com.evoting.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@Table(name = "permission")
public class Permission {
    private int id;
    private String permission;
    private Set<Person> persons = new HashSet<Person>(0);

    public Permission() {
    }

    public Permission(int id, String permission) {
        this.id = id;
        this.permission = permission;
    }

    public Permission(String permission) {
        this.permission = permission;
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

    @Column(name = "permission", nullable = false)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @ManyToMany(mappedBy = "permissions")
    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
