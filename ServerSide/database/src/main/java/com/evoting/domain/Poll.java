package com.evoting.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Azhar on 2016/07/14.
 */

@Entity
@Audited
@Table(name="poll")
public class Poll {
    private int id;
    private String poll;
    private Set<PoliticalParty> politicalParties = new HashSet<PoliticalParty>(0);

    public Poll() {
    }

    public Poll(int id, String poll) {
        this.id = id;
        this.poll = poll;
    }

    public Poll(String poll) {
        this.poll = poll;
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

    @Column(name = "poll", nullable = false, length = 50)
    public String getPoll() {
        return poll;
    }

    public void setPoll(String poll) {
        this.poll = poll;
    }

    @ManyToMany(mappedBy = "polls")
    public Set<PoliticalParty> getPoliticalParties() {
        return politicalParties;
    }

    public void setPoliticalParties(Set<PoliticalParty> politicalParties) {
        this.politicalParties = politicalParties;
    }
}
