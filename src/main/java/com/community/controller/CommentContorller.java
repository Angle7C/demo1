package com.community.controller;

import com.community.model.ResultJson;
import com.community.model.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class CommentContorller {

    @GetMapping("comment/{questionId}")
    public ResultJson<Comment> getQuestion(
            @PathVariable("questionId") Integer questionId) {
        return null;
    }
}
