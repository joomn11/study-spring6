package tobyspring.myboot;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.myboot.data.OrderRepository;
import tobyspring.myboot.order.Order;

public class DataClient {

    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
//        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.ONE);
                repository.save(order2);
                return null;
            });
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            System.out.println("restore process..");
        }


    }
}
