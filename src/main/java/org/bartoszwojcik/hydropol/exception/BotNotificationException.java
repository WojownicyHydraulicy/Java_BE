package org.bartoszwojcik.hydropol.exception;

/**
 * Exception thrown when an error occurs during bot notification processing.
 */
public class BotNotificationException extends RuntimeException {

    /**
     * Constructs a new BotNotificationException with the specified detail message.
     *
     * @param message the detail message
     */
    public BotNotificationException(String message) {
        super(message);
    }

    /**
     * Constructs a new BotNotificationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public BotNotificationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new BotNotificationException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public BotNotificationException(Throwable cause) {
        super(cause);
    }
}
