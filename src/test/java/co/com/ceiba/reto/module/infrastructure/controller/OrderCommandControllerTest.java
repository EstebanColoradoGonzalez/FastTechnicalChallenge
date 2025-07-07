package co.com.ceiba.reto.module.infrastructure.controller;

import co.com.ceiba.reto.ApplicationMock;
import co.com.ceiba.reto.module.application.command.CreateOrderCommand;
import co.com.ceiba.reto.module.infrastructure.testdatabuilder.CreateOrderCommandTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create order successfully and return 201 with correct response")
    void createOrderShouldReturnCreatedAndOrderDTO() throws Exception {
        CreateOrderCommand command = new CreateOrderCommandTestDataBuilder().build();

        mockMvc.perform(post("/orders")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId", notNullValue()))
                .andExpect(jsonPath("$.items", hasSize(command.getItems().size())))
                .andExpect(jsonPath("$.totalAmount", greaterThan(0.0)))
                .andExpect(jsonPath("$.status", anyOf(is("PAID"), is("FAILED"))));
    }
}