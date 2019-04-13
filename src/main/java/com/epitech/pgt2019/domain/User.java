package com.epitech.pgt2019.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A User.
 */
@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("firstname")
    private String firstname;

    @Field("name")
    private String name;

    @Field("title")
    private String title;

    @Field("birthdate")
    private LocalDate birthdate;

    @DBRef
    @Field("city")
    @JsonIgnoreProperties("users")
    private City city;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public User firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public User title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public User birthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public City getCity() {
        return city;
    }

    public User city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (user.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", name='" + getName() + "'" +
            ", title='" + getTitle() + "'" +
            ", birthdate='" + getBirthdate() + "'" +
            "}";
    }
}
