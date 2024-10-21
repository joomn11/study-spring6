package tobyspring.myboot;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.myboot.payment.Payment;
import tobyspring.myboot.payment.PaymentService;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
//        ObjectFactory objectFactory = new ObjectFactory();
//        PaymentService service = objectFactory.paymentService();

//        PaymentService service = new PaymentService(new WebApiExRateProvider());
//        PaymentService service = new PaymentService(new SimpleExRateProvider());

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService service = beanFactory.getBean(PaymentService.class);

        Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);

        TimeUnit.SECONDS.sleep(1);

        Payment payment2 = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment2);

        TimeUnit.SECONDS.sleep(2);

        Payment payment3 = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment3);
    }
}
