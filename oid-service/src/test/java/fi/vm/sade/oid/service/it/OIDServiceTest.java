package fi.vm.sade.oid.service.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import fi.vm.sade.dbunit.annotation.DataSetLocation;
import fi.vm.sade.oid.service.OIDService;
import fi.vm.sade.oid.service.types.NodeClassCode;
import fi.vm.sade.oid.service.types.NodeClassData;
import fi.vm.sade.oid.util.JTACleanInsertTestExecutionListener;

@ContextConfiguration(locations = "classpath:spring/test-context.xml")
@TestExecutionListeners(listeners = { JTACleanInsertTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DataSetLocation("classpath:test-data.xml")
public class OIDServiceTest {

    @Autowired
    OIDService oidService;

    @Test
    public void testGenerateOid() {

        try {
            String oid = oidService.newOidByClassValue("24");
            assertTrue(oid.startsWith("1.2.246.562.24."));

            oid = oidService.newOid(NodeClassCode.TOIMIPAIKAT);
            assertTrue(oid.startsWith("1.2.246.562.10."));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetNodeClasses() {
        try {
            List<NodeClassData> classes = oidService.getNodeClasses();
            assertEquals(15, classes.size());
        } catch (Exception e) {
            fail();
        }
    }

}
