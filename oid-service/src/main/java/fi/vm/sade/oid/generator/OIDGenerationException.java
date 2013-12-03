package fi.vm.sade.oid.generator;

/**
 * Exception which is used to indicate errors when OIDs are generated.
 * 
 * @author Eetu Blomqvist
 * 
 */
public class OIDGenerationException extends Exception {

    private static final long serialVersionUID = -2450499277064711317L;

    public OIDGenerationException() {
        super();
    }

    public OIDGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OIDGenerationException(String message) {
        super(message);
    }

    public OIDGenerationException(Throwable cause) {
        super(cause);
    }

}
