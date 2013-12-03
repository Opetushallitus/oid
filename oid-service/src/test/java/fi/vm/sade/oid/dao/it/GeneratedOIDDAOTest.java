package fi.vm.sade.oid.dao.it;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import fi.vm.sade.dbunit.annotation.DataSetLocation;
import fi.vm.sade.oid.dao.GeneratedOIDDAO;
import fi.vm.sade.oid.model.OID;
import fi.vm.sade.oid.util.JTACleanInsertTestExecutionListener;

@ContextConfiguration(locations = "classpath:spring/test-context.xml")
@TestExecutionListeners(listeners = { JTACleanInsertTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DataSetLocation("classpath:test-data.xml")
public class GeneratedOIDDAOTest {

    @Autowired
    GeneratedOIDDAO generatedOIDDAO;

    @Test
    public void testExistsCheck() {
        OID oid = new OID();
        oid.setNode("24");
        oid.setOidValue(123456L);
        oid.setCheckDigit(1);

        generatedOIDDAO.insert(oid);

        boolean exists = generatedOIDDAO.oidAlreadyExists(123456L);
        assertTrue(exists);
    }
}
