package cn.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.FeedbackDao;
import cn.rest.entity.Feedback;
import cn.rest.entity.Shop;
import cn.rest.entity.User;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.service.FeedbackService;
import cn.rest.service.ShopService;
import cn.rest.service.UserService;
import static cn.rest.service.impl.BaseUtils.*;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Override
    public void add(Feedback feedback) {
        paramNotNull(feedback);
        User user = userService.getById(feedback.getFb_user_id());
        if (user == null) {
            throw ErrorUtils.get(ErrorCode.UserNotFoundError);
        }
        Shop shop = shopService.getById(feedback.getFb_shop_id());
        if (shop == null) {
            throw ErrorUtils.get(ErrorCode.ShopNotFoundError);
        }
        validMobilePhone(feedback.getFb_feedback_phone());
        validFeedbackType(feedback.getFb_feedback_type());
        feedbackDao.add(feedback);
    }

    @Override
    public void updateByIdSelective(Feedback feedback) {
        paramNotNull(feedback);
        paramNotNull(feedback.getFb_feedback_id());
        feedbackDao.updateByIdSelective(feedback);
    }

    @Override
    public Feedback getById(Integer fb_feedback_id) {
        paramNotNull(fb_feedback_id);
        return feedbackDao.getById(fb_feedback_id);
    }

    @Override
    public void updateById(Feedback feedback) {
        paramNotNull(feedback);
        paramNotNull(feedback.getFb_feedback_id());
        paramNotNull(feedback.getFb_feedback_status());
        feedbackDao.updateById(feedback);
    }

    @Override
    public void deleteById(Integer fb_feedback_id) {
        paramNotNull(fb_feedback_id);
        feedbackDao.deleteById(fb_feedback_id);
    }

    @Override
    public List<Feedback> getByShopId(Integer fb_shop_id) {
        paramNotNull(fb_shop_id);
        return feedbackDao.getByShopId(fb_shop_id);
    }

    @Override
    public List<Feedback> getByUserId(Integer fb_user_id) {
        paramNotNull(fb_user_id);
        return feedbackDao.getByUserId(fb_user_id);
    }

    @Override
    public List<Feedback> getByUserIdLimit(Integer fb_user_id, int fb_start,
            int fb_count) {
        paramNotNull(fb_user_id);
        return feedbackDao.getByUserIdLimit(fb_user_id, fb_start, fb_count);
    }

    @Override
    public List<Feedback> getByShopIdLimit(Integer fb_shop_id, int fb_start,
            int fb_count) {
        paramNotNull(fb_shop_id);
        return feedbackDao.getByShopIdLimit(fb_shop_id, fb_start, fb_count);
    }

}
