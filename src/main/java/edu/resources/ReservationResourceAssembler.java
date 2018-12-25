package edu.resources;

import edu.controllers.CarsRestController;
import edu.controllers.ClientRestController;
import edu.controllers.ReservationRestController;
import edu.entities.Reservation;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ReservationResourceAssembler extends ResourceAssemblerSupport<Reservation, ReservationResource> {
    public ReservationResourceAssembler() {
        super(ReservationRestController.class, ReservationResource.class);
    }

    @Override
    public ReservationResource toResource(Reservation reservation) {
        ReservationResource resource = createResourceWithId(reservation.getId(), reservation);
        resource.setID(reservation.getId());
        resource.setDateFrom(reservation.getDateFrom());
        resource.setDateTo(reservation.getDateTo());

        int relCarId = reservation.getCar().getId();
        resource.add(linkTo(methodOn(CarsRestController.class).getCar(relCarId)).withRel("car"));
        int relClientId = reservation.getClient().getId();
        resource.add(linkTo(methodOn(ClientRestController.class).getClient(relClientId)).withRel("client"));
        return resource;
    }
}
