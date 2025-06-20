package org.bartoszwojcik.hydropol.exception;

/**
 * Exception thrown when a registration process fails.
 */
public class RegistrationException extends RuntimeException {

    /**
     * Constructs a new RegistrationException with the specified detail message.
     *
     * @param message the detail message
     */
    public RegistrationException(String message) {
        super(message);
    }

    /**
     * Constructs a new RegistrationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new RegistrationException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public RegistrationException(Throwable cause) {
        super(cause);
    }
}
