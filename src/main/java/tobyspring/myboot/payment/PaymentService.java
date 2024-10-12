package tobyspring.myboot.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final ExRateProvider provider;

    public PaymentService(ExRateProvider exRateProvider) {
        provider = exRateProvider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = provider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30L);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

}


