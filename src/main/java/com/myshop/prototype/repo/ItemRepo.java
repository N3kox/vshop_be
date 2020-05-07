package com.myshop.prototype.repo;

import com.myshop.prototype.bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    public List<Item> getByVerify(Integer verify);

}
