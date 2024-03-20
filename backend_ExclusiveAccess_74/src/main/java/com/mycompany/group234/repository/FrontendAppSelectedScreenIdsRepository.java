package com.mycompany.group234.repository;

import com.mycompany.group234.model.jointable.FrontendAppSelectedScreenIds;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class FrontendAppSelectedScreenIdsRepository extends SimpleJpaRepository<FrontendAppSelectedScreenIds, String> {
    private final EntityManager em;
    public FrontendAppSelectedScreenIdsRepository(EntityManager em) {
        super(FrontendAppSelectedScreenIds.class, em);
        this.em = em;
    }
    @Override
    public List<FrontendAppSelectedScreenIds> findAll() {
        return em.createNativeQuery("Select * from \"ExclusiveAccess\".\"FrontendAppSelectedScreenIds\"", FrontendAppSelectedScreenIds.class).getResultList();
    }
}