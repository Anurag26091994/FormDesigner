package com.FormDesigner.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserDetails")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private Long mobileNumber;
    private String email;
    private String password;
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    private Date modificationDate;

    public UserDetails() {
    }

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public UserDetails(Long userId, String name, Long mobileNumber, String email, String password,
                       String address, Date creationDate, Date modificationDate) {
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
