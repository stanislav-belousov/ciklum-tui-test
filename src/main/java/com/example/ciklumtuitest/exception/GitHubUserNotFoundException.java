package com.example.ciklumtuitest.exception;

public class GitHubUserNotFoundException extends RuntimeException {

    public GitHubUserNotFoundException() {
        super("User name not found");
    }

}
