package com.myshop.prototype.bean;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private Long uid;
    private Long iid;
    private Integer quantity;   //订购物品数量
    private Integer statement;  //订单状态:0-未付款(购物车), 1-已付款

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatement() {
        return statement;
    }

    public void setStatement(Integer state) {
        this.statement = state;
    }
}
