package tobyspring.myboot.exrate;

import java.math.BigDecimal;
import tobyspring.myboot.payment.ExRateProvider;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("Not supported currency");
    }
}
