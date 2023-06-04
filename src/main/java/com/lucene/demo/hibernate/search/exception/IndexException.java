package com.lucene.demo.hibernate.search.exception;

/**
 * Custom Exception used during Lucene index build.
 */
public class IndexException extends Exception {
    public IndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexException(String message) {
        super(message);
    }
}
