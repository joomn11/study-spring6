package tobyspring.myboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.myboot.exrate.CachedExRateProvider;
import tobyspring.myboot.exrate.WebApiExRateProvider;
import tobyspring.myboot.payment.ExRateProvider;
import tobyspring.myboot.payment.PaymentService;

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
