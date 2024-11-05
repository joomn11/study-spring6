package tobyspring.myboot;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.myboot.order.Order;
import tobyspring.myboot.order.OrderService;

public class OrderClient {

    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService service = beanFactory.getBean(OrderService.class);

        Order order = service.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);

    }
}
