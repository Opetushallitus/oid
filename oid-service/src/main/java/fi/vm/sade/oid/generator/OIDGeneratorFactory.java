package fi.vm.sade.oid.generator;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * Factory which can be used to obtain correct instance of {@link OIDGenerator}
 * for certain node.
 * 
 * @author Eetu Blomqvist
 * 
 */
public class OIDGeneratorFactory {

    private static final Logger logger = LoggerFactory.getLogger(OIDGeneratorFactory.class);

    private List<OIDGenerator> generators;

    private OIDGenerator defaultGenerator;

    /**
     * Returns the first suitable {@link OIDGenerator} instance which can be
     * found among defined generators.
     * 
     * Returns configured default generator if no suitable generator is found in
     * the list of defined generators AND the default generator is defined.
     * 
     * @param node
     *            The node which the OID is to be generated for.
     * @return The first suitable generator for node which is found.
     * @throws OIDGenerationException
     *             When no generators are found or none of the generators are
     *             suitable.
     */
    public OIDGenerator getOIDGenerator(String node) throws OIDGenerationException {

        OIDGenerator candidate = null;

        if (generators == null && defaultGenerator == null) {
            logger.warn("Couldn't find generators!");
            throw new OIDGenerationException("No generators found!");
        }

        if (generators != null) {
            for (OIDGenerator generator : generators) {
                logger.info("AcceptedNode: " + CollectionUtils.arrayToList(generator.getAcceptedNodeValues()));
                logger.info("node: " + node);
                if (CollectionUtils.arrayToList(generator.getAcceptedNodeValues()).contains(node)) {
                    // if a generator is found, it is used and rest of the
                    // generators are ignored.
                    logger.info("generator found!");
                    logger.info("generator: " + generator);
                    logger.info("AcceptedNode: " + Arrays.toString(generator.getAcceptedNodeValues()));
                    logger.info("node: " + node);

                    candidate = generator;
                    break;
                }
            }
        }

        if (candidate == null && defaultGenerator == null) {
            throw new OIDGenerationException("No Generator found for node " + node);
        } else if(candidate == null) {
            candidate = defaultGenerator;
        }

        logger.info("Generator: " + candidate);

        return candidate;
    }

    public void setGenerators(List<OIDGenerator> generators) {
        this.generators = generators;
    }

    public void setDefaultGenerator(OIDGenerator defaultGenerator) {
        this.defaultGenerator = defaultGenerator;
    }
}
