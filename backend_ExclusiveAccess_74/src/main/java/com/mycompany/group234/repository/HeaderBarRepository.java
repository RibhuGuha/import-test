package com.mycompany.group234.repository;

import com.mycompany.group234.model.HeaderBar;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class HeaderBarRepository extends SimpleJpaRepository<HeaderBar, String> {
    private final EntityManager em;
    public HeaderBarRepository(EntityManager em) {
        super(HeaderBar.class, em);
        this.em = em;
    }
    @Override
    public List<HeaderBar> findAll() {
        return em.createNativeQuery("Select * from \"ExclusiveAccess\".\"HeaderBar\"", HeaderBar.class).getResultList();
    }
}