package com.farmcollector.farmcollector;

import com.farmcollector.farmcollector.core.dto.HarvestRequest;
import com.farmcollector.farmcollector.core.dto.PlantRequest;
import com.farmcollector.farmcollector.core.dto.Season;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class FarmCollectorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void testSubmittingOfPlantedData_success() throws Exception {
        var plantedData = new PlantRequest("MyFarm", Season.SPRING, "Corn", 25.5, 50.5);

        var result = mockMvc.perform(post("/farm/plant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantedData)))
                .andExpect(status().isOk());

        Assertions.assertEquals("true", result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testSubmittingOfharvestedData_success() throws Exception {
        var harvestedData = new HarvestRequest("MyFarm", "Corn", 60.5);

        var result = mockMvc.perform(put("/farm/harvest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(harvestedData)))
                .andExpect(status().isOk());

        Assertions.assertEquals("true", result.andReturn().getResponse().getContentAsString());
    }

}
