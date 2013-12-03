package fi.vm.sade.oid.dao;

import fi.vm.sade.generic.dao.JpaDAO;
import fi.vm.sade.oid.model.OIDBaseData;

/**
 * DAO for base data needed in OID generation
 * 
 * @author Eetu Blomqvist
 * 
 */
public interface OIDBaseDataDAO extends JpaDAO<OIDBaseData, Long> {

    OIDBaseData findByKey(String key);
}
