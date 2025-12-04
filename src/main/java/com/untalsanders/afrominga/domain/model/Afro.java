package com.untalsanders.afrominga.domain.model;

public class Afro {
    private String id;
    private String firstname;
    private String lastname;

    public Afro() {}

    public Afro(String id, String firstname) {
        this.id = id;
        this.firstname = firstname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return String.format("Afro[id='%s', firstname='%s', lastname='%s']", getId(), getFirstname(), getLastname());
    }
}
