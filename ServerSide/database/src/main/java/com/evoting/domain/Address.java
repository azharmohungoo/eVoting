package com.evoting.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by Azhar on 2016/08/21.
 */

@Entity
@Audited
@Table(name = "address")
public class Address {
    private int id;
    private String nodeName;
    private String blockchainAddress;
    private String ipAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "node_name", unique = true, nullable = false)
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    @Column(name = "blockchain_address", unique = true, nullable = false)
    public String getBlockchainAddress() {
        return blockchainAddress;
    }

    public void setBlockchainAddress(String blockchainAddress) {
        this.blockchainAddress = blockchainAddress;
    }
    @Column(name = "ip_address", unique = true, nullable = false)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
