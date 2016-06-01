package cn.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.ShopTaskDao;
import cn.rest.entity.Shop;
import cn.rest.entity.ShopTask;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.service.ShopService;
import cn.rest.service.ShopTaskService;
import static cn.rest.service.impl.BaseUtils.*;

@Service
public class ShopTaskServiceImpl implements ShopTaskService {

    @Autowired
    private ShopTaskDao shopTaskDao;

    @Autowired
    private ShopService shopService;

    @Override
    public void add(ShopTask shopTask) {
        paramNotNull(shopTask);
        Shop shop = shopService.getById(shopTask.getFb_shop_id());
        if (shop == null) {
            throw ErrorUtils.get(ErrorCode.ShopNotFoundError);
        }
        validEmail(shopTask.getFb_shop_task_email());
        shopTaskDao.add(shopTask);
    }

    @Override
    public void deleteById(Integer fb_shop_task_id) {
        paramNotNull(fb_shop_task_id);
        shopTaskDao.deleteById(fb_shop_task_id);
    }

    @Override
    public void updateById(ShopTask shopTask) {
        paramNotNull(shopTask);
        paramNotNull(shopTask.getFb_shop_task_id());
        paramNotNull(shopTask.getFb_shop_task_status());
        shopTaskDao.updateById(shopTask);
    }

    @Override
    public void updateByIdSelective(ShopTask shopTask) {
        paramNotNull(shopTask);
        paramNotNull(shopTask.getFb_shop_task_id());
        paramNotNull(shopTask.getFb_shop_task_status());
        shopTaskDao.updateByIdSelective(shopTask);
    }

    @Override
    public ShopTask getById(Integer fb_shop_task_id) {
        paramNotNull(fb_shop_task_id);
        return shopTaskDao.getById(fb_shop_task_id);
    }

    @Override
    public List<ShopTask> getByShopId(Integer fb_shop_id) {
        paramNotNull(fb_shop_id);
        return shopTaskDao.getByShopId(fb_shop_id);
    }

}
