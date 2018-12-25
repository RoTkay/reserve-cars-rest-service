package edu.controllers;

import edu.daos.Dao;
import edu.daos.FindBy;
import edu.entities.Reservation;
import edu.entities.cars.Car;
import edu.entities.clients.Client;
import edu.resources.ReservationResource;
import edu.resources.ReservationResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReservationRestController {
    private ReservationResourceAssembler reservationResourceAssembler;
    private FindBy<Reservation> reservationDao;
    private Dao<Client> clientDao;
    private Dao<Car> carDao;

    public ReservationRestController(ReservationResourceAssembler reservationResourceAssembler, FindBy<Reservation> reservationDao, Dao<Client> clientDao, Dao<Car> carDao) {
        this.reservationResourceAssembler = reservationResourceAssembler;
        this.reservationDao = reservationDao;
        this.clientDao = clientDao;
        this.carDao = carDao;
    }

    @GetMapping("/reservations/{id}")
    public ReservationResource getReservation(@PathVariable int id) {
        return reservationResourceAssembler.toResource(reservationDao.findOne(id));
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable int id) {
        Reservation reservation = reservationDao.findOne(id);
        reservationDao.delete(id);
        return ResponseEntity.ok().body(reservation);
    }
}
