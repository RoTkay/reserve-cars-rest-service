package edu.entities.clients;

import javax.persistence.Embeddable;
import java.sql.Date;

@Embeddable
public class PassportInformation {
    private String firstname;
    private String lastname;
    private Date birthDate;

    public PassportInformation(){}
    public PassportInformation(String firstname, String lastname, Date birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
