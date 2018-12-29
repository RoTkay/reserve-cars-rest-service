package edu.entities.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Car(String brand, String model, int year, double price) {
        this.description = new CarDescription(brand, model, year);
        this.price = price;
    }

}
