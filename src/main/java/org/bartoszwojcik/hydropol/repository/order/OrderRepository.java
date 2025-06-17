package org.bartoszwojcik.hydropol.repository.order;

import java.util.List;
import org.bartoszwojcik.hydropol.model.classes.Order;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {

    List<Order> findByOrderStatus(String orderStatus, Pageable pageable);

    List<Order> findByAssignedToId(Long assignedTo);

    Boolean existsOrderByAssignedTo(User assignedTo);
}
