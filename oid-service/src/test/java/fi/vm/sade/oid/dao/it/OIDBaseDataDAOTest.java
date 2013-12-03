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
import fi.vm.sade.oid.dao.OIDBaseDataDAO;
import fi.vm.sade.oid.model.OIDBaseData;
import fi.vm.sade.oid.util.JTACleanInsertTestExecutionListener;

@ContextConfiguration(locations = "classpath:spring/test-context.xml")
@TestExecutionListeners(listeners = { JTACleanInsertTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DataSetLocation("classpath:test-data.xml")
public class OIDBaseDataDAOTest {

    @Autowired
    OIDBaseDataDAO oidBaseDataDAO;

    @Test
    public void testGetRootNode() {
        OIDBaseData rootNode = oidBaseDataDAO.findByKey("rootNode");
        assertNotNull(rootNode);
        assertEquals("1.2.246.562", rootNode.getValue());
    }
}
