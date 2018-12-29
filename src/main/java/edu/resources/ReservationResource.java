package edu.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResource extends ResourceSupport {
    private int identifier;
    private Date dateFrom;
    private Date dateTo;
}
