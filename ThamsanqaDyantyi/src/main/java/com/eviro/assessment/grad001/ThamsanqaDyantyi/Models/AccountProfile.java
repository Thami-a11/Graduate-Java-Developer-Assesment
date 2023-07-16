package com.eviro.assessment.grad001.ThamsanqaDyantyi.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "AccountProfile")
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String ImageHttp;

    public AccountProfile() {
    }

    public AccountProfile(Long id, String name, String surname, String imageHttp) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        ImageHttp = imageHttp;
    }

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



    public String getImage() {
        return ImageHttp;
    }

    public void setImage(String imageHttp) {
        ImageHttp = imageHttp;
    }

    @Override
    public String toString() {
        return "AccountProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Image='" + ImageHttp + '\'' +
                '}';
    }
}
