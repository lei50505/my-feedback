package cn.rest.dao;

import java.util.List;

import cn.rest.entity.Feedback;

public interface FeedbackDao {
    void add(Feedback feedback);
    void updateByIdSelective(Feedback feedback);
    void updateById(Feedback feedback);
    void deleteById(Integer fb_feedback_id);
    Feedback getById(Integer fb_feedback_id);
    List<Feedback> getByShopId(Integer fb_shop_id);
    List<Feedback> getByUserId(Integer fb_user_id);
    List<Feedback> getByUserIdLimit(Integer fb_user_id,int fb_start,int fb_count);
    List<Feedback> getByShopIdLimit(Integer fb_shop_id,int fb_start,int fb_count);
}
