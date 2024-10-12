package tobyspring.myboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider();
    }
}
