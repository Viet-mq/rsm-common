package com.edso.resume.lib.exception;

/**
 * The type Session expired exception.
 */
public class SessionException extends Exception {

    /**
     * Instantiates a new Session expired exception.
     */
    public SessionException() {
    }

    /**
     * Instantiates a new Session expired exception.
     *
     * @param s the s
     */
    public SessionException(String s) {
        super(s);
    }

    /**
     * Instantiates a new Session expired exception.
     *
     * @param s         the s
     * @param throwable the throwable
     */
    public SessionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * Instantiates a new Session expired exception.
     *
     * @param throwable the throwable
     */
    public SessionException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Instantiates a new Session expired exception.
     *
     * @param s         the s
     * @param throwable the throwable
     * @param b         the b
     * @param b1        the b 1
     */
    public SessionException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
