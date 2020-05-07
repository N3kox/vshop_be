package com.myshop.prototype.impl;

import com.myshop.prototype.bean.Item;
import com.myshop.prototype.repo.ItemRepo;
import com.myshop.prototype.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public Item insertItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public void deleteItem(Long iid) {
        itemRepo.deleteById(iid);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public List<Item> selectAll() {
        return itemRepo.findAll();
    }

    @Override
    public List<Item> getByVerify(Integer verify) {
        return itemRepo.getByVerify(verify);
    }

    @Override
    public Optional<Item> selectItem(Long iid) {
        return itemRepo.findById(iid);
    }
}
