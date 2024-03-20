package com.mycompany.group234.repository;

import com.mycompany.group234.model.AppConfiguration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class AppConfigurationRepository extends SimpleJpaRepository<AppConfiguration, String> {
    private final EntityManager em;
    public AppConfigurationRepository(EntityManager em) {
        super(AppConfiguration.class, em);
        this.em = em;
    }
    @Override
    public List<AppConfiguration> findAll() {
        return em.createNativeQuery("Select * from \"ExclusiveAccess\".\"AppConfiguration\"", AppConfiguration.class).getResultList();
    }
}