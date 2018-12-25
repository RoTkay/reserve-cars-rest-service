package edu.entities.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.entities.Reservation;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Integer id;
    @Embedded
    private PassportInformation passportInformation;
    @OneToMany(mappedBy = "client", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Reservation> reservations;

    public Client() {}
    public Client(PassportInformation passportInformation) {
        this.passportInformation = passportInformation;
    }
    public Client(String firstname, String lastname, Date birthDate) {
        this.passportInformation = new PassportInformation(firstname, lastname, birthDate);
    }
    public Client(int id, String firstname, String lastname, Date birthDate) {
        this.id = id;
        this.passportInformation = new PassportInformation(firstname, lastname, birthDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PassportInformation getPassportInformation() {
        return passportInformation;
    }

    public void setPassportInformation(PassportInformation passportInformation) {
        this.passportInformation = passportInformation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
