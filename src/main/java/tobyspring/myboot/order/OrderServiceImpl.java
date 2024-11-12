package tobyspring.myboot.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return reqs.stream()
                   .map(req -> createOrder(req.no(), req.total()))
                   .collect(Collectors.toList());
    }
}
