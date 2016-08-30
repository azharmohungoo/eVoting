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
    private String partyName;
    private Set<Poll> polls = new HashSet<Poll>(0);
    private int nationalVoteCount = 0;
    private int provincialVoteCount = 0;
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

    @Column(name = "party_name", unique = true, nullable = false)
    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
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

    @Column(name = "national_vote_count", unique = false, nullable = false)
    public int getNationalVoteCount() {
        return nationalVoteCount;
    }

    public void setNationalVoteCount(int nationalVoteCount) {
        this.nationalVoteCount = nationalVoteCount;
    }

    @Column(name = "provincial_vote_count", unique = false, nullable = false)
    public int getProvincialVoteCount() {
        return provincialVoteCount;
    }

    public void setProvincialVoteCount(int provincialVoteCount) {
        this.provincialVoteCount = provincialVoteCount;
    }

    @Column(name = "blockchain_node_address", unique = true, nullable = false)
    public String getBlockchainNodeAddress() {
        return blockchainNodeAddress;
    }

    public void setBlockchainNodeAddress(String blockchainNodeAddress) {
        this.blockchainNodeAddress = blockchainNodeAddress;
    }

    @Column(name = "ip_address", unique = true, nullable = false)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
