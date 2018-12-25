package edu.resources;

import edu.entities.cars.CarDescription;
import org.springframework.hateoas.ResourceSupport;

public class CarResource extends ResourceSupport {
    private int id;
    private CarDescription description;
    private double price;

    public CarResource() { }

    public CarResource(int id, CarDescription description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public CarDescription getDescription() {
        return description;
    }

    public void setDescription(CarDescription description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
