package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.Feedback;
import cn.rest.response.ResponseUtils;
import cn.rest.service.FeedbackService;

@RequestMapping("/feedback")
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(Feedback feedback) {
        feedbackService.add(feedback);
        return ResponseUtils.get();
    }

}
