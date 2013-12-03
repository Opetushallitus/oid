package fi.vm.sade.oid.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import fi.vm.sade.generic.dao.AbstractJpaDAOImpl;
import fi.vm.sade.oid.dao.NodeClassDAO;
import fi.vm.sade.oid.model.NodeClass;

/**
 * JPA implementation of {@link NodeClassDAO}
 * 
 * @author Eetu Blomqvist
 * 
 */
@Repository
public class NodeClassDAOJpaImpl extends AbstractJpaDAOImpl<NodeClass, Long> implements NodeClassDAO {

    @Override
    public NodeClass findByClassCode(String code) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<NodeClass> query = cb.createQuery(NodeClass.class);

        Root<NodeClass> root = query.from(NodeClass.class);
        query.select(root);

        Predicate whereClause = cb.equal(root.get("classCode"), code);
        query.where(whereClause);

        return getEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public NodeClass findByNodeValue(String value) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<NodeClass> query = cb.createQuery(NodeClass.class);

        Root<NodeClass> root = query.from(NodeClass.class);
        query.select(root);

        Predicate whereClause = cb.equal(root.get("nodeValue"), value);
        query.where(whereClause);

        return getEntityManager().createQuery(query).getSingleResult();
    }
}
