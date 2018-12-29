package edu.entities.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Client(String firstname, String lastname, Date birthDate) {
        this.passportInformation = new PassportInformation(firstname, lastname, birthDate);
    }

}
