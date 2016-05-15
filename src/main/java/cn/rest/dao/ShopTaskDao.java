package cn.rest.dao;

import cn.rest.entity.ShopTask;

public interface ShopTaskDao {
    void insert(ShopTask shopTask);
    void updateByPkSelective(ShopTask shopTask);
    void deleteByPk(Integer fb_shop_task_id);
    ShopTask selectByPk(Integer fb_shop_task_id);
}
