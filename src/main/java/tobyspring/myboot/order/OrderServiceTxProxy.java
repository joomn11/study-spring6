package tobyspring.myboot.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class OrderServiceTxProxy implements OrderService {

    private final PlatformTransactionManager transactionManager;
    private final OrderService target;

    public OrderServiceTxProxy(PlatformTransactionManager transactionManager, OrderService target) {
        this.transactionManager = transactionManager;
        this.target = target;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return new TransactionTemplate(transactionManager).execute(status -> target.createOrder(no, total));
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status -> target.createOrders(reqs));
    }
}
