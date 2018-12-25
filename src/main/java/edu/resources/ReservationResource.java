package edu.resources;

import org.springframework.hateoas.ResourceSupport;

import java.sql.Date;

public class ReservationResource extends ResourceSupport {
    private int id;
    private Date dateFrom;
    private Date dateTo;

    public ReservationResource() {}
    public ReservationResource(int id, Date dateFrom, Date dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
