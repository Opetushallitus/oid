package fi.vm.sade.oid.generator.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fi.vm.sade.oid.dao.GeneratedOIDDAO;
import fi.vm.sade.oid.dao.OIDBaseDataDAO;
import fi.vm.sade.oid.generator.OIDGenerator;
import fi.vm.sade.oid.model.OID;
import fi.vm.sade.oid.model.OIDBaseData;

/**
 * Impementation of {@link OIDGenerator} which follows the guide lines specified
 * by OPH
 * (<a>https://confluence.csc.fi/pages/viewpage.action?pageId=14026462</a>)
 * 
 * @author Eetu Blomqvist
 * 
 */
public class RandomLuhnOIDGenerator implements OIDGenerator {

    @Autowired
    GeneratedOIDDAO generatedOIDDAO;

    @Autowired
    OIDBaseDataDAO oidBaseDataDAO;

    private String rootNode;

    private static final String[] NODE_VALUES = { "10" };

    @Override
    @Transactional
    public String generateOID(String node) {

        if (rootNode == null) {
            OIDBaseData rootNodeData = oidBaseDataDAO.findByKey("rootNode");
            rootNode = rootNodeData.getValue();
        }

        long min = 1000000000L;
        long max = 10000000000L;

        boolean generateNew = true;

        long number;
        OID newOid = null;

        while (generateNew) {
            // generate random long inside range
            Random r = new Random();
            number = min + ((long) (r.nextDouble() * (max - min)));

            if (!generatedOIDDAO.oidAlreadyExists(number)) {

                newOid = new OID();
                newOid.setNode(node);
                newOid.setOidValue(number);
                newOid.setCheckDigit(luhnChecksum(number));

                generatedOIDDAO.insert(newOid);

                generateNew = false;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(rootNode).append(".").append(node).append(".").append(newOid.getOidValue())
                .append(newOid.getCheckDigit());
        return builder.toString();
    }

    @Override
    public String[] getAcceptedNodeValues() {
        return NODE_VALUES;
    }

    public int luhnChecksum(Long oid) {
        String oidStr = oid.toString();

        int sum = 0;
        boolean alternate = true;

        for (int i = oidStr.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(oidStr.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return 10 - sum % 10;
    }

}
