package tobyspring.myboot.order;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.myboot.OrderConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder() {
        // given
        // when
        Order order = orderService.createOrder("0100", BigDecimal.TEN);

        // then
        Assertions.assertThat(order.getId()).isGreaterThan(0);
        System.out.println(order.getId());
    }
}
