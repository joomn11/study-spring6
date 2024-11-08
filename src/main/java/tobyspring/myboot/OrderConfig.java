package tobyspring.myboot;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.myboot.data.JdbcOrderRepository;
import tobyspring.myboot.order.OrderRepository;
import tobyspring.myboot.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(DataSource ds) {
        return new JdbcOrderRepository(ds);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        return new OrderService(orderRepository, transactionManager);
    }
}
