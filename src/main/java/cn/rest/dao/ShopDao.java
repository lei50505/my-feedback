package cn.rest.dao;

import cn.rest.entity.Shop;

public interface ShopDao {
    void add(Shop shop);
    void updateByIdSelective(Shop shop);
    void updateById(Shop shop);
    Shop getById(Integer fb_shop_id);
    Shop getByEmail(String fb_shop_email);
    Shop getByToken(String fb_shop_token);
}
