package by.grudnitsky.tacocloud.data;

import by.grudnitsky.tacocloud.Order;
import by.grudnitsky.tacocloud.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);
}
