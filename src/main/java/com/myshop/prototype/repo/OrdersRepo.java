package com.myshop.prototype.repo;

import com.myshop.prototype.bean.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> getByUid(Long uid);
    List<Orders> getByUidAndStatement(Long uid, Integer statement);
}
