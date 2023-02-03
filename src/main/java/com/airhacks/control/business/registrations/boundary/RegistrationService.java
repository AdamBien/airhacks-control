package com.airhacks.control.business.registrations.boundary;

import com.airhacks.control.business.registrations.entity.Attendee;
import java.util.List;
import javax.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 *
 * @author adam-bien.com
 */
public class RegistrationService {

    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction et;

    @PostConstruct
    public void init() {
        this.emf = Persistence.createEntityManagerFactory("airhacks");
        this.em = this.emf.createEntityManager();
        this.et = this.em.getTransaction();
    }

    public List<Attendee> all() {
        return this.em.createNamedQuery(Attendee.findAll).getResultList();
    }

    public Attendee save(Attendee attendee) {
        et.begin();
        Attendee merged = this.em.merge(attendee);
        et.commit();
        return merged;
    }

    public void save() {
        et.begin();
        em.flush();
        et.commit();
    }

    public void remove(Attendee attendee) {
        et.begin();
        this.em.remove(attendee);
        et.commit();
    }

    public void close() {
        et.begin();
        et.commit();
        em.close();
    }
}
