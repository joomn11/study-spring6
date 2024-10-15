package tobyspring.myboot.payment;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import tobyspring.myboot.exrate.WebApiExRateProvider;

class PaymentServiceTest {

    @Test
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // get exRate
        assertThat(payment.getExRate()).isNotNull();

        // get converted amount
        assertThat(payment.getConvertedAmount())
            .isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // get valid time
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}