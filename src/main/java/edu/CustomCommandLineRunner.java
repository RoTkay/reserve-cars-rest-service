package edu;

import edu.daos.jpa.UserJpaDao;
import edu.entities.Reservation;
import edu.entities.User;
import edu.entities.cars.Car;
import edu.entities.clients.Client;
import edu.daos.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {
    @Autowired
    private Dao<Client> clientDao;
    @Autowired
    private Dao<Car> carDao;
    @Autowired
    private Dao<Reservation> reservationDao;
    @Autowired
    private UserJpaDao userJpaDao;
    private String date = "2018-08-12";

    @Override
    public void run(String... args) throws Exception {
        List<Client> clients = getClients();
        List<Car> cars = getCars();

        clients.stream()
                .forEach(client -> clientDao.save(client));

        cars.stream()
                .forEach(car -> carDao.save(car));

        clients.stream()
                .forEach(client -> cars.stream()
                        .forEach(car ->
                                reservationDao.save(new Reservation(Date.valueOf(date), Date.valueOf(date), client, car))));

        userJpaDao.save(new User(new BCryptPasswordEncoder().encode("password"), "root"));
    }

    private List<Client> getClients() {
        return Arrays.asList(
                new Client("Rostislav", "Kachanov", Date.valueOf("1999-06-04")),
                new Client("Dmytro", "Rusnak", Date.valueOf("1999-08-24")),
                new Client("Nikita", "Dzuba", Date.valueOf("1999-08-20")),
                new Client("Max", "Pustovoit", Date.valueOf("1999-03-05")));
    }

    private List<Car> getCars() {
        return Arrays.asList(new Car("Bmw", "Z4", 2012, 1_020.00),
                new Car("Mercedes", "GL", 2016, 1_200.00),
                new Car("Nissan", "Note", 2014, 120.00));
    }
}
