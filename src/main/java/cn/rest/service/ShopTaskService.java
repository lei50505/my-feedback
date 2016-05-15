package cn.rest.service;

import cn.rest.entity.ShopTask;

public interface ShopTaskService {
    void add(ShopTask shopTask);
    void deleteByPk(int shopTaskId);
}
