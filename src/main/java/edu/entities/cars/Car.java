package edu.entities.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.entities.Reservation;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Integer id;
    @Embedded
    private CarDescription description;
    @OneToMany(mappedBy = "car", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Reservation> reservations;
    private double price;

    public Car() {
    }
    public Car(CarDescription description, double price) {
        this.description = description;
        this.price = price;
    }

    public Car(String brand, String model, int year, double price) {
        this.description = new CarDescription(brand, model, year);
        this.price = price;
    }
    public Car(int id, String brand, String model, int year, double price) {
        this.id = id;
        this.description = new CarDescription(brand, model, year);
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarDescription getDescription() {
        return description;
    }

    public void setDescription(CarDescription description) {
        this.description = description;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", description=" + description +
                ", reservations=" + reservations +
                ", price=" + price +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 &&
                Objects.equals(description, car.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price);
    }
}
