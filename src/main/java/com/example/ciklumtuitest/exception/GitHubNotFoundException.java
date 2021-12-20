package com.example.ciklumtuitest.exception;

/**
 * Custom implementation of {@link RuntimeException} that throws when GitHub user with
 * specified username not found
 */
public class GitHubNotFoundException extends RuntimeException {

    /**
     * Constructs new GitHubUserNotFoundException with specifies detail message
     */
    public GitHubNotFoundException() {
        super("Object not found");
    }

    public GitHubNotFoundException(String message) {
        super(message);
    }

}
