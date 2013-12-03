package fi.vm.sade.oid.dao;

import fi.vm.sade.generic.dao.JpaDAO;
import fi.vm.sade.oid.model.NodeClass;

/**
 * DAO for node classes
 * 
 * @author Eetu Blomqvist
 * 
 */
public interface NodeClassDAO extends JpaDAO<NodeClass, Long> {

    NodeClass findByClassCode(String code);

    NodeClass findByNodeValue(String value);
}
