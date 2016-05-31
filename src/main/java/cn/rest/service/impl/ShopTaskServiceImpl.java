package cn.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.ShopTaskDao;
import cn.rest.entity.ShopTask;
import cn.rest.service.ShopTaskService;

@Service
public class ShopTaskServiceImpl implements ShopTaskService {

    @Autowired
    private ShopTaskDao shopTaskDao;

    @Override
    public void add(ShopTask shopTask) {
        shopTaskDao.insert(shopTask);
    }

    @Override
    public void deleteById(int fb_shop_task_id) {
        shopTaskDao.deleteByPk(fb_shop_task_id);
    }

}
