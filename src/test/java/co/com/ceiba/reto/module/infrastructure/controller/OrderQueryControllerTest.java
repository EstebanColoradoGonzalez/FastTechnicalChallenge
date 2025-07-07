package co.com.ceiba.reto.module.infrastructure.controller;

import co.com.ceiba.reto.ApplicationMock;
import co.com.ceiba.reto.module.infrastructure.testdatabuilder.OrderDTOTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("getOrderById should return order successfully with status 200")
    void getOrderByIdShouldReturnOrderAndStatusOk() throws Exception {
        var orderId = 1L;
        var expectedOrder = new OrderDTOTestDataBuilder().withOrderId(orderId).build();

        mockMvc.perform(get("/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId", is(expectedOrder.getOrderId().intValue())))
                .andExpect(jsonPath("$.items", hasSize(expectedOrder.getItems().size())))
                .andExpect(jsonPath("$.totalAmount", comparesEqualTo(expectedOrder.getTotalAmount().doubleValue())))
                .andExpect(jsonPath("$.status", is(expectedOrder.getStatus())));
    }
}