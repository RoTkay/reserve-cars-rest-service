package edu.entities.clients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PassportInformation {
    private String firstname;
    private String lastname;
    private Date birthDate;
}
