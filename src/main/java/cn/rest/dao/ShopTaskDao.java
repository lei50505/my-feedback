package cn.rest.dao;

import java.util.List;

import cn.rest.entity.ShopTask;

public interface ShopTaskDao {
    void add(ShopTask shopTask);

    void updateById(ShopTask shopTask);

    void updateByIdSelective(ShopTask shopTask);

    void deleteById(Integer fb_shop_task_id);

    ShopTask getById(Integer fb_shop_task_id);
    
    List<ShopTask> getByShopId(Integer fb_shop_id);
}
