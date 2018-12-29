package edu.resources;

import edu.entities.cars.CarDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResource extends ResourceSupport {
    private int identifier;
    private CarDescription description;
    private double price;
}
