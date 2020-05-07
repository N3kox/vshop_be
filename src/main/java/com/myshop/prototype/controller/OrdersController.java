package com.myshop.prototype.controller;

import com.myshop.prototype.bean.Orders;
import com.myshop.prototype.impl.OrdersImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrdersController extends OrdersImpl {

    // state mark
    private Integer OUTSTANDING = Integer.valueOf(0);
    private Integer FINISHED = Integer.valueOf(1);

    @GetMapping("/all")
    @ResponseBody
    public List<Orders> getAllOrder(){
        return getAllOrder();
    }

    // TODO : 表连接
    //获取用户订单
    //可选param : statement
    @GetMapping("/mine")
    @ResponseBody
    public List<Orders> getMineOrder(@Param("uid") Long uid, @Param("statement") Integer statement){
        return selectUserOrders(uid, statement);
    }

    @PostMapping("/create")
    @ResponseBody
    public Orders createOrder(@Param("uid") Long uid, @Param("iid") Long iid, @Param("quantity") Integer quantity, @Param("statement") Integer statement){
        if(uid == null || iid == null || quantity == null)
            return null;
        Orders no = new Orders();
        no.setUid(uid);
        no.setIid(iid);
        no.setQuantity(quantity);
        no.setStatement((statement == OUTSTANDING || statement == FINISHED) ? statement : OUTSTANDING);
        insertOrders(no);
        return no;
    }

    //订单结束
    @PutMapping("/finish")
    @ResponseBody
    public Orders finishOrder(@Param("oid") Long oid){
        Optional<Orders> no = selectOrders(oid);
        no.get().setStatement(FINISHED);
        return updateOrders(no.get());
    }


}
