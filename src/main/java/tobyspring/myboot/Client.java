package tobyspring.myboot;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {
//        PaymentService service = new PaymentService(new WebApiExRateProvider());
        PaymentService service = new PaymentService(new SimpleExRateProvider());
        Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
