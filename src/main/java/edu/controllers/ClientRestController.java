package edu.controllers;

import edu.daos.Dao;
import edu.daos.FindBy;
import edu.entities.Reservation;
import edu.entities.cars.Car;
import edu.entities.clients.Client;
import edu.resources.ClientResource;
import edu.resources.ClientResourceAssembler;
import edu.resources.ReservationResource;
import edu.resources.ReservationResourceAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clients")
public class ClientRestController {
    private ReservationResourceAssembler reservationResourceAssembler;
    private FindBy<Reservation> reservationDao;
    private Dao<Car> carDao;
    private Dao<Client> clientDao;
    private ClientResourceAssembler clientResourceAssembler;

    public ClientRestController(ReservationResourceAssembler reservationResourceAssembler, FindBy<Reservation> reservationDao, Dao<Car> carDao, Dao<Client> clientDao, ClientResourceAssembler clientResourceAssembler) {
        this.reservationResourceAssembler = reservationResourceAssembler;
        this.reservationDao = reservationDao;
        this.carDao = carDao;
        this.clientDao = clientDao;
        this.clientResourceAssembler = clientResourceAssembler;
    }

    @GetMapping
    public Resources<ClientResource> getAllClients() {
        Resources<ClientResource> resources = new Resources<>(clientDao.findAll()
                .stream()
                .map(clientResourceAssembler::toResource)
                .collect(Collectors.toList()));
        resources.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString(), "self"));
        return resources;
    }

    @GetMapping(path = "/{id}")
    public ClientResource getClient(@PathVariable int id) {
        return clientResourceAssembler.toResource(clientDao.findOne(id));
    }

    @PostMapping
    public ResponseEntity addClient(@RequestBody Client client) {
        clientDao.save(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@RequestBody Client client,
                                       @PathVariable int id) {
        client.setId(id);
        clientDao.update(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable int id) {
        Client client = clientDao.findOne(id);
        clientDao.delete(id);
        return ResponseEntity.ok().body(client);
    }

    @GetMapping("/{id}/reservations")
    public Resources<ReservationResource> getClientAllReservations(@PathVariable int id) {
        Client client = clientDao.findOne(id);
        Resources<ReservationResource> reservationList = new Resources<> (reservationDao.findByClient(client)
                .stream()
                .map(reservationResourceAssembler::toResource)
                .collect(Collectors.toList()));
        return reservationList;
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity addReservation(@PathVariable int id,
                                         @RequestBody Reservation reservation) {
        Client client = clientDao.findOne(id);
        reservation.setClient(client);
        reservationDao.save(reservation);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/reservations/{id}")
                .buildAndExpand(reservation.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
