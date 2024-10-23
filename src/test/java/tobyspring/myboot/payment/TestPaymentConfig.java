package tobyspring.myboot.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.myboot.api.ApiTemplate;
import tobyspring.myboot.exrate.WebApiExRateProvider;

@Configuration
//@ComponentScan
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
