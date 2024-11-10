package tobyspring.myboot.order;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.myboot.OrderConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {
        // given
        // when
        Order order = orderService.createOrder("0100", BigDecimal.TEN);

        // then
        Assertions.assertThat(order.getId()).isGreaterThan(0);
        System.out.println(order.getId());
    }

    @Test
    void createOrders() {
        // given
        List<OrderReq> orderReqs = List.of(
            new OrderReq("0200", BigDecimal.ONE),
            new OrderReq("0201", BigDecimal.TEN)
        );

        // when
        List<Order> orders = orderService.createOrders(orderReqs);

        // then
        Assertions.assertThat(orders).hasSize(2);
        orders.forEach(order -> Assertions.assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() {
        // given
        List<OrderReq> orderReqs = List.of(
            new OrderReq("0300", BigDecimal.ONE),
            new OrderReq("0300", BigDecimal.TEN)
        );

        Assertions.assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                  .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient jdbcClient = JdbcClient.create(dataSource);
        List<Order> list = jdbcClient.sql("select * from orders").query(Order.class).list();
        Assertions.assertThat(list).hasSize(0);

    }
}
