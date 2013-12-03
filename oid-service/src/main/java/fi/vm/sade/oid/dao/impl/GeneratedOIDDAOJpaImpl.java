package fi.vm.sade.oid.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import fi.vm.sade.generic.dao.AbstractJpaDAOImpl;
import fi.vm.sade.oid.dao.GeneratedOIDDAO;
import fi.vm.sade.oid.model.OID;

/**
 * JPA implementation of {@link GeneratedOIDDAO}
 * 
 * @author Eetu Blomqvist
 * 
 */
@Repository
public class GeneratedOIDDAOJpaImpl extends AbstractJpaDAOImpl<OID, Long> implements GeneratedOIDDAO {

    @Override
    public boolean oidAlreadyExists(Long oid) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<OID> root = cq.from(OID.class);
        cq.select(cb.count(root.get("oidValue")));

        Predicate where = cb.equal(root.get("oidValue"), oid);
        cq.where(where);

        Long amount = getEntityManager().createQuery(cq).getSingleResult();
        if (amount > 0) {
            return true;
        }
        return false;
    }
}
