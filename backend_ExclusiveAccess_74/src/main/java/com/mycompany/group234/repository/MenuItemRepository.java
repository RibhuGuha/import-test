package com.mycompany.group234.repository;

import com.mycompany.group234.model.MenuItem;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class MenuItemRepository extends SimpleJpaRepository<MenuItem, String> {
    private final EntityManager em;
    public MenuItemRepository(EntityManager em) {
        super(MenuItem.class, em);
        this.em = em;
    }
    @Override
    public List<MenuItem> findAll() {
        return em.createNativeQuery("Select * from \"ExclusiveAccess\".\"MenuItem\"", MenuItem.class).getResultList();
    }
}