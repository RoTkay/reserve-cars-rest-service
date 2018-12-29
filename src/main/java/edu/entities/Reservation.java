package edu.entities;

import edu.entities.cars.Car;
import edu.entities.clients.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Integer id;
    private Date dateFrom;
    private Date dateTo;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Car car;

    public Reservation(Date dateFrom, Date dateTo, Client client, Car car) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.client = client;
        this.car = car;
    }
}
