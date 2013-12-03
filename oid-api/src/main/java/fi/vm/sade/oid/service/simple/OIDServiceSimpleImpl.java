/*
 * Copyright (c) 2013 The Finnish Board of Education - Opetushallitus
 *
 * This program is free software:  Licensed under the EUPL, Version 1.1 or - as
 * soon as they will be approved by the European Commission - subsequent versions
 * of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at: http://www.osor.eu/eupl/
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 */
package fi.vm.sade.oid.service.simple;

import fi.vm.sade.oid.service.ExceptionMessage;
import fi.vm.sade.oid.service.OIDService;
import fi.vm.sade.oid.service.types.NodeClassCode;
import fi.vm.sade.oid.service.types.NodeClassData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This is pretty much the mock implementation, it just uses "date" (yyyMMddhhmmssSSS + random).
 * No validation characters.
 *
 * We are here trusting that:
 * <ul>
 *   <li>The place thats is using the OID is managing the value and "master of the data", so the "uniqueness" can be restricted a bit.</li>
 *   <li>The uniqueness of this method is "good enough" (seldom generated, not in "fast" batch)</li>
 * </ul>
 *
 * Examples of OIDs generated:
 * <pre>
 * 1.2.246.562.5.2013052409561013582688
 * 1.2.246.562.5.2013052409562689910998
 * </pre>
 *
 * Created this since using the "mock" implementation in real application felt "bad". And
 * also it feels warm and fuzzy to see some human readable meaning in the generated "OID".
 *
 * <b>NOT QUARANTEED TO BE UNIQUE!</b> But probably good enough (currentTimeMillis + random * 100000).
 */
public class OIDServiceSimpleImpl implements OIDService {

    private static Logger log = Logger.getAnonymousLogger();

    private String root = "1.2.246.562.";
    private String[] values = new String[]{"5", "6", "10", "11", "12", "13", "14", "16", "17", "18", "19", "20",
        "22", "24", "27"};
    private NodeClassCode[] codes = new NodeClassCode[] {
                                        NodeClassCode.TEKN_5,
                                        NodeClassCode.TEKN_6,
                                        NodeClassCode.TOIMIPAIKAT,
                                        NodeClassCode.ASIAKIRJAT,
                                        NodeClassCode.OHJELMISTOT,
                                        NodeClassCode.LAITTEET,
                                        NodeClassCode.PALVELUT,
                                        NodeClassCode.LASKUTUS,
                                        NodeClassCode.LOGISTIIKKA,
                                        NodeClassCode.SANOMALIIKENNE,
                                        NodeClassCode.REKISTERINPITAJA,
                                        NodeClassCode.NAYTETUNNISTE,
                                        NodeClassCode.TILAP_ASIAKAS,
                                        NodeClassCode.HENKILO,
                                        NodeClassCode.ROOLI
                                        };

    @Override
    public String newOidByClassValue(String nodeClassValue) throws ExceptionMessage {
        return root + nodeClassValue + "." + generateRandom();
    }

    @Override
    public List<NodeClassData> getNodeClasses() throws ExceptionMessage {
        List<NodeClassData> list = new ArrayList<NodeClassData>();

        for (int i = 0; i < values.length; i++) {
            NodeClassData data = new NodeClassData();
            data.setClassCode(codes[i]);
            data.setNodeValue(values[i]);
            data.setDescription(i + "");
            list.add(data);
        }

        return list;
    }

    @Override
    public String newOid(NodeClassCode nodeClass) throws ExceptionMessage {

        int valueIndex = -1;
        for (int i = 0; i < codes.length; ++i) {
            if (codes[i].equals(nodeClass)) {
                valueIndex = i;
                break;
            }
        }
        if (valueIndex < 0) {
            log.warning("It seems that there is a new NodeClassCode defined, please update " +
                    this.getClass().getSimpleName() + "! NodeClassCode = " + nodeClass);
            // Generate TEKN_5 oid
            valueIndex = 0;
        }

        return root + values[valueIndex] + "." + generateRandom();
    }

    private String generateRandom() {
        return String.format("%1$tY%<tm%<td%<tH%<tM%<tS%<tL%2$05d", new Date(), new Random().nextInt(100000));
    }
}
