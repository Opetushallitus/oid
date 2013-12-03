package fi.vm.sade.oid.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import fi.vm.sade.oid.generator.impl.RandomIBMCheckOIDGenerator;
import fi.vm.sade.oid.generator.impl.RandomLuhnOIDGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import fi.vm.sade.oid.dao.GeneratedOIDDAO;
import fi.vm.sade.oid.dao.OIDBaseDataDAO;
import fi.vm.sade.oid.model.OID;
import fi.vm.sade.oid.model.OIDBaseData;

@RunWith(BlockJUnit4ClassRunner.class)
public class RandomOIDGeneratorTest {

    @Mock
    GeneratedOIDDAO generatedOIDDAO;

    @Mock
    OIDBaseDataDAO oidBaseDataDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateOid() {

        // set mocks properly
        OIDBaseData rootNode = new OIDBaseData();
        rootNode.setKey("rootNode");
        rootNode.setValue("1.2.246.562");

        when(oidBaseDataDAO.findByKey("rootNode")).thenReturn(rootNode);
        when(generatedOIDDAO.oidAlreadyExists(anyLong())).thenReturn(false);
        when(generatedOIDDAO.insert(any(OID.class))).thenReturn(null);

        RandomLuhnOIDGenerator gen = new RandomLuhnOIDGenerator();
        ReflectionTestUtils.setField(gen, "generatedOIDDAO", generatedOIDDAO);
        ReflectionTestUtils.setField(gen, "oidBaseDataDAO", oidBaseDataDAO);

        String oid = gen.generateOID("24");

        assertTrue(oid.startsWith("1.2.246.562.24."));
        System.out.println("Generated: " + oid);
        int luhn = gen.luhnChecksum(7992739871L);
        assertEquals(3,luhn);
        luhn = gen.luhnChecksum(123456L);
        assertEquals(6,luhn);
        luhn = gen.luhnChecksum(111111L);
        assertEquals(1,luhn);
        luhn = gen.luhnChecksum(1L);
        assertEquals(8,luhn);
    }

    @Test
    public void testIBMCheckGenerateOid() {

        // set mocks properly
        OIDBaseData rootNode = new OIDBaseData();
        rootNode.setKey("rootNode");
        rootNode.setValue("1.2.246.562");

        when(oidBaseDataDAO.findByKey("rootNode")).thenReturn(rootNode);
        when(generatedOIDDAO.oidAlreadyExists(anyLong())).thenReturn(false);
        when(generatedOIDDAO.insert(any(OID.class))).thenReturn(null);

        RandomIBMCheckOIDGenerator gen = new RandomIBMCheckOIDGenerator();
        ReflectionTestUtils.setField(gen, "generatedOIDDAO", generatedOIDDAO);
        ReflectionTestUtils.setField(gen, "oidBaseDataDAO", oidBaseDataDAO);

        String oid = gen.generateOID("24");

        assertTrue(oid.startsWith("1.2.246.562.24."));
        System.out.println("Generated: " + oid);
    }


}
