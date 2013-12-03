package fi.vm.sade.oid.dao.it;

import static org.junit.Assert.*;

import java.util.List;

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
import fi.vm.sade.oid.dao.NodeClassDAO;
import fi.vm.sade.oid.model.NodeClass;
import fi.vm.sade.oid.util.JTACleanInsertTestExecutionListener;

@ContextConfiguration(locations = "classpath:spring/test-context.xml")
@TestExecutionListeners(listeners = { JTACleanInsertTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DataSetLocation("classpath:test-data.xml")
public class NodeClassDAOTest {

    @Autowired
    NodeClassDAO nodeClassDAO;

    @Test
    public void testListNodeClasses() {
        List<NodeClass> classes = nodeClassDAO.findAll();
        assertEquals(15, classes.size());
    }

    @Test
    public void testFindByNodeValue() {

        NodeClass nodeClass = nodeClassDAO.findByNodeValue("24");
        assertNotNull(nodeClass);
        assertEquals("HENKILO", nodeClass.getClassCode());
    }

    @Test
    public void testFindByClassCode() {

        NodeClass nodeClass = nodeClassDAO.findByClassCode("HENKILO");
        assertNotNull(nodeClass);
        assertEquals("24", nodeClass.getNodeValue());
    }

}
