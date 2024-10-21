package tobyspring.myboot.payment;


import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

    @Test
    void prepare() throws IOException {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1000), valueOf(10_000));
        testAmount(valueOf(3000), valueOf(30_000));

        // get valid time
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // get exRate
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // get converted amount
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}