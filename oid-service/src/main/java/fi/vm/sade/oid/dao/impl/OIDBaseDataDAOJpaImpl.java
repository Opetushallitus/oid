package fi.vm.sade.oid.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import fi.vm.sade.generic.dao.AbstractJpaDAOImpl;
import fi.vm.sade.oid.dao.OIDBaseDataDAO;
import fi.vm.sade.oid.model.OIDBaseData;

/**
 * JPA implementation of {@link OIDBaseDataDAO}
 * 
 * @author Eetu Blomqvist
 * 
 */
@Repository
public class OIDBaseDataDAOJpaImpl extends AbstractJpaDAOImpl<OIDBaseData, Long> implements OIDBaseDataDAO {

    @Override
    public OIDBaseData findByKey(String key) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OIDBaseData> q = cb.createQuery(OIDBaseData.class);
        Root<OIDBaseData> root = q.from(OIDBaseData.class);

        q.select(root);

        Predicate where = cb.equal(root.get("key"), key);

        q.where(where);

        return getEntityManager().createQuery(q).getSingleResult();
    }

}
