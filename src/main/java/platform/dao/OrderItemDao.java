package platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import platform.entity.OrderItem;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
    /**
     * 根据订单Id查询
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(int orderId);

    @Modifying
    @Transactional
    @Query(value = "SELECT distinct product_id from order_item",nativeQuery = true)
    List<Integer> findAllPid();

    List<OrderItem> findByProductId(int pid);

}
