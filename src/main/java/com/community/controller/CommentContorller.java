package com.community.controller;

import com.community.dto.CommentDTO;
import com.community.dto.SearchDTO;
import com.community.dto.UserDTO;
import com.community.enums.ErrorEnum;
import com.community.enums.SucessEnum;
import com.community.model.ResultJson;
import com.community.model.Comment;
import com.community.model.User;
import com.community.services.CommentService;
import com.community.services.QuestionService;
import com.community.utils.UserUntils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
public class CommentContorller {
    @Autowired
    private CommentService commentService;
    @Autowired
    private QuestionService questionService;
    //light:ok
    @PostMapping("comment/{questionId}")
    public ResultJson<CommentDTO> getQuestion(
            HttpServletRequest request,
            @PathVariable("questionId") @NotNull Long questionId,
            @RequestBody @NotNull SearchDTO.Page page
            ) {
        PageInfo<CommentDTO> pageInfo = commentService.findforQuestion(page.getPageSize(), page.getPageIndex(), questionId);
        ResultJson json=new ResultJson(SucessEnum.COMMENT_IN_QUESTION);
        json.setDatas(pageInfo.getList());
        page.setTotalPage(pageInfo.getSize());
        json.setData(page);
        return json;
    }
    @PostMapping("pushComment")
    //light:ok
    public ResultJson<CommentDTO> pushComment(
            HttpServletRequest request,
            @RequestBody CommentDTO commentDTO
    ){
        User user = UserUntils.checkUser(request);
        Assert.notNull(user, ErrorEnum.CHECK_USER_LOGIN.getMessage());
        Comment comment = commentDTO.toModel();
        comment.setCreateId(user.getAccountId());
        commentService.updateOrCreate(comment);
        ResultJson<CommentDTO>  json= new ResultJson<>(SucessEnum.PUSH_COMMENT);
        questionService.addComment(comment.getQuestionId());
        json.setData(new CommentDTO(comment,new UserDTO(user)));
        return json;
    }
    //light:ok
    @PostMapping("comment/addLike/{commentId}")
    public ResultJson addLike(
            @PathVariable("commentId") @NotNull Long commentId
    ){
        commentService.addLike(commentId);
        return new ResultJson(SucessEnum.ADD_LIKE);
    }
    //light:ok
    @DeleteMapping("comment")
    public ResultJson detelteComment(
            @RequestBody @NotNull(message = "没有ID列表") @Size(min = 1,message = "没有ID列表") List<Long> listID
    ){
        commentService.delCommentList(listID);
        return new ResultJson(SucessEnum.DEL_COMMENT);
    }
}
