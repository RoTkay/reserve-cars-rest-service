package edu.resources;

import edu.entities.clients.PassportInformation;
import org.springframework.hateoas.ResourceSupport;

public class ClientResource extends ResourceSupport {
    private int id;
    private PassportInformation passportInformation;

    public ClientResource() {}
    public ClientResource(int id, PassportInformation passportInformation) {
        this.id = id;
        this.passportInformation = passportInformation;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public PassportInformation getPassportInformation() {
        return passportInformation;
    }

    public void setPassportInformation(PassportInformation passportInformation) {
        this.passportInformation = passportInformation;
    }
}
