package cn.rest.dao;

import cn.rest.entity.Feedback;

public interface FeedbackDao {
    void insert(Feedback feedback);
    void updateByPkSelective(Feedback feedback);
    Feedback selectByPk(Integer fb_feedback_id);
}
