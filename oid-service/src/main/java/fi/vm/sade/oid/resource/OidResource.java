package fi.vm.sade.oid.resource;

import fi.vm.sade.oid.service.OIDService;
import fi.vm.sade.oid.service.types.NodeClassCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author Antti Salonen
 */
@Component
@Path("/oid")
public class OidResource {
	
	private static final Logger logger = LoggerFactory.getLogger(OidResource.class);

    @Autowired
    private OIDService oidService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create")
    public Response create() {
    	try {
    		return Response.ok(oidService.newOid(NodeClassCode.HENKILO)).build();
    	}
    	catch (Exception e) {
    		logger.error("Error in generating OID, error stack: ", e);
    	}
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

}
