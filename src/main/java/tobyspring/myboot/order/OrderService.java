package tobyspring.myboot.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
//        return new TransactionTemplate(transactionManager).execute(status -> {
//            orderRepository.save(order);
//            return order;
//        });
        orderRepository.save(order);
        return order;
    }

    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status -> reqs.stream()
                                                                                 .map(req -> createOrder(req.no(), req.total()))
                                                                                 .collect(Collectors.toList()));
    }
}
