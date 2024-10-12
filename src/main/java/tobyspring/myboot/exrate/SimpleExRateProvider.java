package tobyspring.myboot.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import tobyspring.myboot.payment.ExRateProvider;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("Not supported currency");
    }
}
