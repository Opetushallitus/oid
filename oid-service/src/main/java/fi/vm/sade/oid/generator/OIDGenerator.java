package fi.vm.sade.oid.generator;

/**
 * OIDGenerator defines interface for classes capable of OID generation.
 * 
 * @author Eetu Blomqvist
 * 
 */
public interface OIDGenerator {

    /**
     * Generates new OID
     * 
     * @return generated OID
     */
    public String generateOID(String node) throws OIDGenerationException;

    /**
     * Returns the node values for which the generator is designed to generate
     * OIDs for.
     * 
     * @return
     */
    public String[] getAcceptedNodeValues();
}
