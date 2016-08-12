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
@Table(name="political_party")
public class PoliticalParty {
    private int id;
    private String party;
    private Set<Poll> polls = new HashSet<Poll>(0);
    private int nationalVoteCount;
    private int provincialVoteCount;
    private String blockchainNodeAddress;
    private String ipAddress;

    public PoliticalParty() {
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

    @Column(name = "party", unique = true, nullable = false, length = 25)
    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "political_party_poll",
            joinColumns = { @JoinColumn(name = "polls") },
            inverseJoinColumns = { @JoinColumn(name = "political_parties")} )
    public Set<Poll> getPolls() {
        return polls;
    }

    public void setPolls(Set<Poll> polls) {
        this.polls = polls;
    }

    @Column(name = "nationalVoteCount")
    public int getNationalVoteCount() {
        return nationalVoteCount;
    }

    public void setNationalVoteCount(int nationalVoteCount) {
        this.nationalVoteCount = nationalVoteCount;
    }

    @Column(name = "provincialVoteCount")
    public int getProvincialVoteCount() {
        return provincialVoteCount;
    }

    public void setProvincialVoteCount(int provincialVoteCount) {
        this.provincialVoteCount = provincialVoteCount;
    }

    @Column(name = "blockchainNodeAddress", unique = true, nullable = false)
    public String getBlockchainNodeAddress() {
        return blockchainNodeAddress;
    }

    public void setBlockchainNodeAddress(String blockchainNodeAddress) {
        this.blockchainNodeAddress = blockchainNodeAddress;
    }

    @Column(name = "ipAddress", unique = true, nullable = false)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
