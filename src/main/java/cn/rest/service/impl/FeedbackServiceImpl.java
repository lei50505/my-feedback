package cn.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.FeedbackDao;
import cn.rest.entity.Feedback;
import cn.rest.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;
    @Override
    public void add(Feedback feedback) {
        feedbackDao.insert(feedback);
    }

}
