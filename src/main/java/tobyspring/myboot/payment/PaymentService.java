package tobyspring.myboot.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final ExRateProvider provider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        provider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = provider.getExRate(currency);
        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exRate, LocalDateTime.now(clock));
    }

}


