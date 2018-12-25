package edu.resources;

import edu.controllers.CarsRestController;
import edu.entities.cars.Car;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CarResourceAssembler extends ResourceAssemblerSupport<Car, CarResource> {
    public CarResourceAssembler() {
        super(CarsRestController.class, CarResource.class);
    }

    @Override
    public CarResource toResource(Car car) {
        CarResource resource = createResourceWithId(car.getId(), car);
        resource.setDescription(car.getDescription());
        resource.setPrice(car.getPrice());
        resource.setID(car.getId());

        resource.add(linkTo(methodOn(CarsRestController.class).getAllCars()).withRel("all-cars"));
        resource.add(linkTo(methodOn(CarsRestController.class).getCarAllReservations(car.getId())).withRel("reservations"));
        return resource;
    }
}
