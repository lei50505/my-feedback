package cn.rest.dao;

import cn.rest.entity.Shop;

public interface ShopDao {
    void insert(Shop shop);
    void updateByPkSelective(Shop shop);
    Shop selectByPk(Integer fb_shop_id);
}