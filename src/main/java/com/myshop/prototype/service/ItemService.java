package com.myshop.prototype.service;

import com.myshop.prototype.bean.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item insertItem(Item item);
    void deleteItem(Long iid);
    Item updateItem(Item item);
    List<Item> selectAll();
    Optional<Item> selectItem(Long iid);
    List<Item> getByVerify(Integer verify);
}
