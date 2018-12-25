package edu.entities;

import edu.entities.cars.Car;
import edu.entities.clients.Client;

import javax.persistence.*;
import java.sql.Date;

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

    public Reservation(){}
    public Reservation(Date dateFrom, Date dateTo, Client client, Car car) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.client = client;
        this.car = car;
    }
    public Reservation(int id, Date dateFrom, Date dateTo, Client client, Car car) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.client = client;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
