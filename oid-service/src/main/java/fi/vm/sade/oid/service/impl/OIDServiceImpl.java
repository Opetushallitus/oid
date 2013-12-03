package fi.vm.sade.oid.service.impl;

import fi.vm.sade.oid.dao.NodeClassDAO;
import fi.vm.sade.oid.generator.OIDGenerationException;
import fi.vm.sade.oid.generator.OIDGenerator;
import fi.vm.sade.oid.generator.OIDGeneratorFactory;
import fi.vm.sade.oid.model.NodeClass;
import fi.vm.sade.oid.service.ExceptionMessage;
import fi.vm.sade.oid.service.OIDService;
import fi.vm.sade.oid.service.types.NodeClassCode;
import fi.vm.sade.oid.service.types.NodeClassData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for {@link OIDService}
 * 
 * @author Eetu Blomqvist
 * 
 */
public class OIDServiceImpl implements OIDService {

    private static final Logger logger = LoggerFactory.getLogger(OIDServiceImpl.class);

    @Autowired
    private NodeClassDAO nodeClassDAO;

    @Autowired
    OIDGeneratorFactory oidGeneratorFactory;

    @Override
    public String newOid(NodeClassCode nodeClassCode) throws ExceptionMessage {

        logger.info("newOid Called with nodeClass " + nodeClassCode);

        if (nodeClassCode == null) {
            throw new ExceptionMessage("NodeClassCode not specified!");
        }

        try {
            NodeClass nodeClass = nodeClassDAO.findByClassCode(nodeClassCode.name());
            OIDGenerator generator = oidGeneratorFactory.getOIDGenerator(nodeClass.getNodeValue());
            return generator.generateOID(nodeClass.getNodeValue());

        } catch (NoResultException e) {
        	// This exception and stack can be ignored, reason for exception is known
            throw new ExceptionMessage("Invalid node code: " + nodeClassCode);
        } catch (OIDGenerationException e) {
            throw new ExceptionMessage("OID generation failed", e);
        }
    }

    @Override
    public String newOidByClassValue(String nodeValue) throws ExceptionMessage {

        if (nodeValue == null) {
            throw new ExceptionMessage("Node value not specified!");
        }

        try {
            nodeClassDAO.findByNodeValue(nodeValue);
            OIDGenerator generator = oidGeneratorFactory.getOIDGenerator(nodeValue);
            return generator.generateOID(nodeValue);

        } catch (NoResultException e) {
        	// This exception and stack can be ignored, reason for exception is known
            throw new ExceptionMessage("Invalide node value: " + nodeValue);
        } catch (OIDGenerationException e) {
            throw new ExceptionMessage("OID generation failed", e);
        }
    }

    @Override
    public List<NodeClassData> getNodeClasses() throws ExceptionMessage {

        logger.info("getNodeClasses() called");
        List<NodeClass> nodeClasses = nodeClassDAO.findAll();

        List<NodeClassData> dataList = new ArrayList<NodeClassData>();

        for (NodeClass nodeClass : nodeClasses) {
            NodeClassData data = new NodeClassData();
            data.setClassCode(NodeClassCode.valueOf(nodeClass.getClassCode()));
            data.setNodeValue(nodeClass.getNodeValue());
            data.setDescription(nodeClass.getDescription());

            dataList.add(data);
        }

        return dataList;
    }

}
