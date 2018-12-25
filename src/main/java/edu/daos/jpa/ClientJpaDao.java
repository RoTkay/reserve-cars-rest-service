package edu.daos.jpa;

import edu.daos.Dao;
import edu.entities.clients.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ClientJpaDao implements Dao<Client> {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Client> findAll() {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    @Override
    public Client findOne(int idClient) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c WHERE c.id = :id", Client.class);
        query.setParameter("id", idClient);
        return query.getSingleResult();
    }

    @Override
    public void save(Client client) {
        entityManager.persist(client);
    }

    @Override
    public Client update(Client client) {
        return entityManager.merge(client);
    }

    @Override
    public void delete(int idClient) {
        Client client = findOne(idClient);
        entityManager.remove(client);
    }

    public Client findClientByUsername(String username) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c WHERE c.passportInformation.firstname = :username", Client.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public void deleteAll() {
        entityManager.createNativeQuery("DELETE FROM client").executeUpdate();
    }
}
