package edu.daos.jpa;

import edu.daos.Dao;
import edu.daos.FindBy;
import edu.entities.Reservation;
import edu.entities.cars.Car;
import edu.entities.clients.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ReservationJpaDao implements Dao<Reservation>, FindBy<Reservation> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reservation> findAll() {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT c FROM Reservation c", Reservation.class);
        return query.getResultList();
    }

    @Override
    public Reservation findOne(int idReservation) {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT c FROM Reservation c WHERE c.id = :id", Reservation.class);
        query.setParameter("id", idReservation);
        return query.getSingleResult();
    }

    @Override
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return entityManager.merge(reservation);
    }

    @Override
    public void delete(int idReservation) {
        Reservation reservation = findOne(idReservation);
        entityManager.remove(reservation);
    }

    @Override
    public List<Reservation> findByClient(Client client) {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.client = :client", Reservation.class);
        query.setParameter("client", client);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findByCar(Car car) {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.car = :car", Reservation.class);
        query.setParameter("car", car);
        return query.getResultList();
    }

    @Override
    public void deleteAll() {
        entityManager.createNativeQuery("DELETE FROM reservation").executeUpdate();
    }
}
