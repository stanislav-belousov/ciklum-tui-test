package com.example.ciklumtuitest.client;

import com.example.ciklumtuitest.exception.GitHubUserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class GitHubUserNotFoundDecoder extends ErrorDecoder.Default {

    public static final String GET_REPOSITORIES_INFO_BY_USER_NAME_METHOD_KEY =
            "GitHubClient#getRepositoriesInfoByUserName(String)";

    @Override
    public Exception decode(String methodKey, Response response) {
        if (methodKey.equals(GET_REPOSITORIES_INFO_BY_USER_NAME_METHOD_KEY)
                && response.status() == 404) {
            return new GitHubUserNotFoundException();
        }

        return super.decode(methodKey, response);
    }

}
