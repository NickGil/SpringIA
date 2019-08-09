package by.grudnitsky.tacocloud.data;

import by.grudnitsky.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}
