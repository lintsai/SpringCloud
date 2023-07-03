package com.example.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
@ContextConfiguration
@TestPropertySource(locations = "classpath:application-Junit.properties")
class BasicControllerTest {
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;
    @Value("${test.version}")
    private String version;
    @Autowired
    private BasicController basicController;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    @Test
    void versionGet() throws Exception {
        String uri = "/version";
        // 模擬POST request
        ResultActions resultActions = this.mockMvc.perform(get(uri));

        // 驗證response code is 200
        resultActions.andExpect(status().isOk())
                .andDo(print())
                // 驗證回傳欄位
                .andExpect(content().string(containsString(version)));
    }

//    @Test
//    void versionPost() throws Exception {
//        String uri = "/version";
//        // 模擬POST request
//        ResultActions resultActions = this.mockMvc.perform(post(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE));
//
//        // 驗證response code is 200
//        resultActions.andExpect(status().isOk())
//                .andDo(print())
//                // 驗證回傳欄位
//                .andExpect(content().string(containsString(version)));
//                .andExpect(jsonPath("$.returnCode").value(returnCode))
//                .andExpect(jsonPath("$.returnMessage").value(returnMessage));
//    }

    @Test
    void version() {
        assertEquals("/version", basicController.version(), version);
    }
}