package com.myshop.prototype.impl;

import com.myshop.prototype.bean.Orders;
import com.myshop.prototype.repo.OrdersRepo;
import com.myshop.prototype.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersImpl implements OrdersService {
    @Autowired
    private OrdersRepo ordersRepo;

    @Override
    public Orders insertOrders(Orders order) {
        return ordersRepo.save(order);
    }

    @Override
    public void deleteOrders(Long oid) {
        ordersRepo.deleteById(oid);
    }

    @Override
    public Orders updateOrders(Orders order) {
        return ordersRepo.save(order);
    }

    @Override
    public List<Orders> selectAll() {
        return ordersRepo.findAll();
    }

    @Override
    public Optional<Orders> selectOrders(Long oid) {
        return ordersRepo.findById(oid);
    }

    @Override
    public List<Orders> selectUserOrders(Long uid, Integer statement) {
        return statement == null ? ordersRepo.getByUid(uid) : ordersRepo.getByUidAndStatement(uid, statement);
    }
}
