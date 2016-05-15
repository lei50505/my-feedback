package cn.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.ShopDao;
import cn.rest.entity.Shop;
import cn.rest.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {
    
    @Autowired
    private ShopDao shopDao;

    @Override
    public void add(Shop shop) {
        shopDao.insert(shop);
    }

}
