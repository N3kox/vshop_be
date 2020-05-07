package com.myshop.prototype.service;

import com.myshop.prototype.bean.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Orders insertOrders(Orders orders);
    void deleteOrders(Long oid);
    Orders updateOrders(Orders orders);
    List<Orders> selectAll();
    Optional<Orders> selectOrders(Long oid);

    List<Orders> selectUserOrders(Long uid, Integer statement);
}
