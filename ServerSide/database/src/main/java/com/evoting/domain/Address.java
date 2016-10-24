package com.evoting.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "address")
public class Address {
    private int id;
    private String nodeName;
    private String ipAddress;
    private String port;
    private String rpcUsername;
    private String rpcPassword;
    private String blockchainAddress;

    public Address() {
    }

    public Address(int id, String nodeName, String ipAddress, String port, String rpcUsername, String rpcPassword, String blockchainAddress) {
        this.id = id;
        this.nodeName = nodeName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.rpcUsername = rpcUsername;
        this.rpcPassword = rpcPassword;
        this.blockchainAddress = blockchainAddress;
    }

    public Address(String nodeName, String ipAddress, String port, String rpcUsername, String rpcPassword, String blockchainAddress) {
        this.nodeName = nodeName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.rpcUsername = rpcUsername;
        this.rpcPassword = rpcPassword;
        this.blockchainAddress = blockchainAddress;
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

    @Column(name = "node_name", nullable = false)
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Column(name = "ip_address", nullable = false)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Column(name = "port", nullable = false)
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Column(name = "rpc_username", nullable = false)
    public String getRpcUsername() {
        return rpcUsername;
    }

    public void setRpcUsername(String rpcUsername) {
        this.rpcUsername = rpcUsername;
    }

    @Column(name = "rpc_password", nullable = false)
    public String getRpcPassword() {
        return rpcPassword;
    }

    public void setRpcPassword(String rpcPassword) {
        this.rpcPassword = rpcPassword;
    }

    @Column(name = "blockchain_address", nullable = false)
    public String getBlockchainAddress() {
        return blockchainAddress;
    }

    public void setBlockchainAddress(String blockchainAddress) {
        this.blockchainAddress = blockchainAddress;
    }
}
