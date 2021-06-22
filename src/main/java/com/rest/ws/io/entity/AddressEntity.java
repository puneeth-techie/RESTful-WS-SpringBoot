package com.rest.ws.io.entity;

import com.rest.ws.shared.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userDetail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserEntity getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserEntity userDetail) {
        this.userDetail = userDetail;
    }
}
