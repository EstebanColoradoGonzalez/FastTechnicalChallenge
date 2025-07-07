package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.module.domain.testdatabuilder.OrderTestDataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentServiceTest {

    @Test
    void executeShouldReturnTrue() {
        var order = new OrderTestDataBuilder().build();
        var paymentService = new PaymentService();

        boolean result = paymentService.execute(order);

        assertTrue(result);
    }
}
