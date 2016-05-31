package cn.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.FeedbackDao;
import cn.rest.entity.Feedback;
import cn.rest.entity.User;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.service.FeedbackService;
import cn.rest.service.UserService;
import static cn.rest.service.impl.ParamUtils.*;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;
    
    @Autowired
    private UserService userService;

    @Override
    public void add(Feedback feedback) {
        paramNotNull(feedback);
        Integer fb_user_id = feedback.getFb_user_id();
        paramNotNull(fb_user_id);
        User user = userService.getUserById(fb_user_id);
        if(user==null){
            throw ErrorUtils.get(ErrorCode.UserNotFoundError);
        }
        paramNotNull(feedback.getFb_shop_id());
        validMobilePhone(feedback.getFb_feedback_phone());
        validFeedbackType(feedback.getFb_feedback_type());
        feedbackDao.insert(feedback);
    }

}
