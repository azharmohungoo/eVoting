package com.evoting.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by Azhar on 2016/10/06.
 */

@Entity
@Audited
@Table(name="activation_station")
public class ActivationStation {
    private int id;
    private String city;
    private String station;

    public ActivationStation() {
    }

    public ActivationStation(int id) {
        this.id = id;
    }

    public ActivationStation(String city, String station) {
        this.city = city;
        this.station = station;
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

    @Column(name = "city", nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "station", nullable = false, length = 50)
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
