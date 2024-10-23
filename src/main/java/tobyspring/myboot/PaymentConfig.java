package tobyspring.myboot;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tobyspring.myboot.api.ApiTemplate;
import tobyspring.myboot.api.ErApiExRateExtractor;
import tobyspring.myboot.api.SimpleApiExecutor;
import tobyspring.myboot.exrate.CachedExRateProvider;
import tobyspring.myboot.exrate.RestTemplateExRateProvider;
import tobyspring.myboot.payment.ExRateProvider;
import tobyspring.myboot.payment.PaymentService;

@Configuration
//@ComponentScan
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate() {
//        return new ApiTemplate();
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
//        return new WebApiExRateProvider(apiTemplate());
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
