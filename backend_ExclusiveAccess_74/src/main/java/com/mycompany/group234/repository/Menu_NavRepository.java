package com.mycompany.group234.repository;

import com.mycompany.group234.model.Menu_Nav;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class Menu_NavRepository extends SimpleJpaRepository<Menu_Nav, String> {
    private final EntityManager em;
    public Menu_NavRepository(EntityManager em) {
        super(Menu_Nav.class, em);
        this.em = em;
    }
    @Override
    public List<Menu_Nav> findAll() {
        return em.createNativeQuery("Select * from \"ExclusiveAccess\".\"Menu_Nav\"", Menu_Nav.class).getResultList();
    }
}