package com.example.ciklumtuitest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(properties = "wiremock.server.port=8081")
@AutoConfigureWireMock
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CiklumTuiTestApplicationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When call service with valid user name " +
            "Then return expected information about user repositories")
    void test_success() throws Exception {
        stubFor(get(urlPathMatching("/github/users/validUserName/repos"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("validUserNameRepositories.json")
                ));

        stubFor(get(urlPathMatching("/github/repos/validUserName/notForkRepository/branches"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("notForkRepositorieBranches.json")
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/validUserName")
                        .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                                "{" +
                                    "\"repositoryName\":\"notForkRepository\"," +
                                    "\"ownerLogin\":\"validUserName\"," +
                                    "\"branches\":[" +
                                        "{" +
                                            "\"name\":\"test\"," +
                                            "\"lastCommitSha\":\"cff9eb47dd75afd0660fd5bc9947d919f28769e2\"" +
                                        "}," +
                                        "{" +
                                            "\"name\":\"master\"," +
                                            "\"lastCommitSha\":\"8b5eed05838a319931a494e5baf8e36667068662\"" +
                                        "}" +
                                    "]" +
                                "}]",
                        true));
    }

    @Test
    @DisplayName("When call service with invalid user name " +
            "Then return not found status with expected details")
    void test_whenUserNameInvalid() throws Exception {
        stubFor(get(urlPathMatching("/github/users/invalidUserName/repos"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/invalidUserName")
                        .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(model().attribute("status", 404))
                .andExpect(model().attribute("Message", "User name not found"));
    }

    @Test
    @DisplayName("When call service with invalid accept header " +
            "Then return not acceptable status with expected details")
    void test_whenAcceptHeaderInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/invalidUserName")
                        .header(ACCEPT, MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isNotAcceptable())
                .andExpect(model().attribute("status", 406))
                .andExpect(model().attribute("Message", "Could not find acceptable representation"));
    }

}
