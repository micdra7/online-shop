package drabik.michal.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name must not be empty")
    @Size(max = 60, message = "Name should have max 60 characters")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Surname must not be empty")
    @Size(max = 60, message = "Surname should have max 60 characters")
    @Column(name = "surname")
    private String surname;

    @NotNull(message = "Country must not be empty")
    @Size(max = 50, message = "Country should have max 50 characters")
    @Column(name = "country")
    private String country;

    @NotNull(message = "City must not be empty")
    @Size(max = 60, message = "City should have max 60 characters")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Street must not be empty")
    @Size(max = 100, message = "Street should have max 100 characters")
    @Column(name = "street")
    private String street;

    @NotNull(message = "House number must not be empty")
    @Max(value = 999, message = "House number cannot exceed 999")
    @Column(name = "house_number")
    private Integer houseNumber;

    @Max(value = 999, message = "Flat number cannot exceed 999")
    @Column(name = "flat_number")
    private Integer flatNumber;

    @NotNull(message = "Email must not be empty")
    @Email(message = "Email does not have a proper form")
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
