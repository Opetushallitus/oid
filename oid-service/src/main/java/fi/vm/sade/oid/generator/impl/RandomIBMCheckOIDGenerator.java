package fi.vm.sade.oid.generator.impl;

import fi.vm.sade.oid.dao.GeneratedOIDDAO;
import fi.vm.sade.oid.dao.OIDBaseDataDAO;
import fi.vm.sade.oid.generator.OIDGenerator;
import fi.vm.sade.oid.model.OID;
import fi.vm.sade.oid.model.OIDBaseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 *
 */
public class RandomIBMCheckOIDGenerator implements OIDGenerator {

    @Autowired
    GeneratedOIDDAO generatedOIDDAO;

    @Autowired
    OIDBaseDataDAO oidBaseDataDAO;

    private String rootNode;

    private static final String[] NODE_VALUES = { "24" };

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
                newOid.setCheckDigit(ibmCheck(number));

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

    private int ibmCheck(Long oid) {
        String oidStr = oid.toString();

        int sum = 0;
        int[] alternate = {7, 3 , 1};

        for (int i = oidStr.length() - 1, j = 0; i >= 0; i--, j++) {
            int n = Integer.parseInt(oidStr.substring(i, i + 1));

            sum += n * alternate[j % 3];
        }

        int checksum =  10 - sum % 10;
        if(checksum == 10) {
            return 0;
        }  else {
            return checksum;
        }
    }

}
