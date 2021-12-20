package com.example.ciklumtuitest.client;

import com.example.ciklumtuitest.exception.GitHubNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of {@link ErrorDecoder} that decode
 * not found username response to {@link GitHubNotFoundException}
 */
@Component
public class GitHubNotFoundDecoder extends ErrorDecoder.Default {

    private static final int NOT_FOUND_STATUS_CODE = 404;

    /**
     * Decodes responses with not 2xx status to the exception.
     * If used client method is getRepositoriesInfoByUserName
     * and response status is 404 (Not found) then return {@link GitHubNotFoundException}
     * or use logic of {@link feign.codec.ErrorDecoder.Default} implementation
     *
     * @param methodKey feign.Feign.configKey of the java method that invoked the request.
     * @param response HTTP response where status is greater than or equal to 300
     * @return decoded exception
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == NOT_FOUND_STATUS_CODE) {
            return decodeNotFound(methodKey, response);
        }

        return super.decode(methodKey, response);
    }

    private Exception decodeNotFound(String methodKey, Response response) {
        if (methodKey.startsWith(GitHubUsersClient.class.getSimpleName())) {
            return new GitHubNotFoundException("User not found");
        } else if (methodKey.startsWith(GitHubReposClient.class.getSimpleName())) {
            return new GitHubNotFoundException("Repository not found");
        } else {
            return new GitHubNotFoundException();
        }
    }

}
