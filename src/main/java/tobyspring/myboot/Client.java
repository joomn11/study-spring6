package tobyspring.myboot;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) throws IOException {
//        ObjectFactory objectFactory = new ObjectFactory();
//        PaymentService service = objectFactory.paymentService();

//        PaymentService service = new PaymentService(new WebApiExRateProvider());
//        PaymentService service = new PaymentService(new SimpleExRateProvider());

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService service = beanFactory.getBean(PaymentService.class);
        Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
