package by.grudnitsky.tacocloud.data;

import by.grudnitsky.tacocloud.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
