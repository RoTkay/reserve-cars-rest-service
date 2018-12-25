package edu.daos.jpa;

import edu.daos.Dao;
import edu.entities.cars.Car;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class CarJpaDao implements Dao<Car> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findAll() {
        TypedQuery<Car> query = entityManager.createQuery("SELECT c FROM Car c", Car.class);
        return query.getResultList();
    }

    @Override
    public Car findOne(int idCar) {
        TypedQuery<Car> query = entityManager.createQuery("SELECT c FROM Car c WHERE c.id = :id", Car.class);
        query.setParameter("id", idCar);
        return query.getSingleResult();
    }

    @Override
    public void save(Car car)
    {
        entityManager.persist(car);
    }

    @Override
    public Car update(Car car) {
        return entityManager.merge(car);
    }

    @Override
    public void delete(int idCar) {
        Car car = findOne(idCar);
        entityManager.remove(car);
    }

    @Override
    public void deleteAll() {
        entityManager.createNativeQuery("DELETE FROM car").executeUpdate();
    }
}
