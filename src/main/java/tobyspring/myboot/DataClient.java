package tobyspring.myboot;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.myboot.data.OrderRepository;
import tobyspring.myboot.order.Order;

public class DataClient {

    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
//        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        System.out.println(order);

        Order order2 = new Order("100", BigDecimal.ONE);
        repository.save(order2);

    }
}
