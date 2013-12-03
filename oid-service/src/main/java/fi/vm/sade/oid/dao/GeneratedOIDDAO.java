package fi.vm.sade.oid.dao;

import fi.vm.sade.generic.dao.JpaDAO;
import fi.vm.sade.oid.model.OID;

/**
 * DAO for generated OIDs
 * 
 * @author Eetu Blomqvist
 * 
 */
public interface GeneratedOIDDAO extends JpaDAO<OID, Long> {

    boolean oidAlreadyExists(Long oid);
}
