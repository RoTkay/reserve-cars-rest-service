package edu.resources;

import edu.entities.clients.PassportInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResource extends ResourceSupport {
    private int identifier;
    private PassportInformation passportInformation;
}
