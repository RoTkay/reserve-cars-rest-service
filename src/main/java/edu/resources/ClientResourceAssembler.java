package edu.resources;

import edu.controllers.ClientRestController;
import edu.entities.clients.Client;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ClientResourceAssembler extends ResourceAssemblerSupport<Client, ClientResource> {
    public ClientResourceAssembler() {
        super(ClientRestController.class, ClientResource.class);
    }

    @Override
    public ClientResource toResource(Client client) {
        ClientResource resource = createResourceWithId(client.getId(), client);
        resource.setIdentifier(client.getId());
        resource.setPassportInformation(client.getPassportInformation());

        resource.add(linkTo(methodOn(ClientRestController.class).getAllClients()).withRel("clients"));
        resource.add(linkTo(methodOn(ClientRestController.class).getClientAllReservations(resource.getIdentifier())).withRel("reservations"));
        return resource;
    }
}
