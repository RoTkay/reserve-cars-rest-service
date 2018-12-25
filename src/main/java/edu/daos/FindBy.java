package edu.daos;

import edu.entities.cars.Car;
import edu.entities.clients.Client;

import java.util.List;

public interface FindBy<T> extends Dao<T> {
    List<T> findByClient(Client client);
    List<T> findByCar(Car car);
}
