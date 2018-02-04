package drabik.michal.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private int houseNumber;

    @Column(name = "flat_number")
    private int flat_number;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private Users user;

    public UserDetails() {}

    public UserDetails(String name, String surname, String street, int houseNumber, int flat_number, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flat_number = flat_number;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFlat_number() {
        return flat_number;
    }

    public void setFlat_number(int flat_number) {
        this.flat_number = flat_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
