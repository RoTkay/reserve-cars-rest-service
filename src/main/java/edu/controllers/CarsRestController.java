package edu.controllers;

import edu.daos.Dao;
import edu.daos.FindBy;
import edu.entities.Reservation;
import edu.entities.cars.Car;
import edu.resources.CarResource;
import edu.resources.CarResourceAssembler;
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
@RequestMapping("/cars")
public class CarsRestController {
    private ReservationResourceAssembler reservationResourceAssembler;
    private FindBy<Reservation> reservationDao;
    private Dao<Car> carDao;
    private CarResourceAssembler carResourceAssembler;

    public CarsRestController(ReservationResourceAssembler reservationResourceAssembler, FindBy<Reservation> reservationDao, Dao<Car> carDao, CarResourceAssembler carResourceAssembler) {
        this.reservationResourceAssembler = reservationResourceAssembler;
        this.reservationDao = reservationDao;
        this.carDao = carDao;
        this.carResourceAssembler = carResourceAssembler;
    }

    @GetMapping
    public Resources<CarResource> getAllCars() {
        Resources<CarResource> resources = new Resources<>(carDao.findAll()
                .stream().map(carResourceAssembler::toResource).collect(Collectors.toList()));
        resources.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString(), "self"));
        return resources;
    }

    @GetMapping(path = "/{id}")
    public CarResource getCar(@PathVariable int id) {
        return carResourceAssembler.toResource(carDao.findOne(id));
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        carDao.save(car);
        URI createdUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(car.getId())
                .toUri();
        return ResponseEntity.created(createdUri).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable int id)  {
        Car car = carDao.findOne(id);
        carDao.delete(id);
        return ResponseEntity.ok().body(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCar(@RequestBody Car car,
                                    @PathVariable int id) {
        car.setId(id);
        carDao.update(car);
        URI updatedUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(car.getId())
                .toUri();
        return ResponseEntity.ok(updatedUri);
    }

    @GetMapping("/{id}/reservations")
    public Resources<ReservationResource> getCarAllReservations(@PathVariable int id) {
        Car car = carDao.findOne(id);
        Resources<ReservationResource> reservationList = new Resources<> (reservationDao.findByCar(car)
                .stream()
                .map(reservationResourceAssembler::toResource)
                .collect(Collectors.toList()));
        return reservationList;
    }
}
