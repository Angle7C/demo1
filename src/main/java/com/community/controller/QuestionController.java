package com.community.controller;

import cn.hutool.core.lang.Assert;
import com.community.dto.QuestionDTO;
import com.community.dto.SearchDTO;
import com.community.dto.UserDTO;
import com.community.enums.ErrorEnum;
import com.community.enums.SucessEnum;
import com.community.hander.TagMap;
import com.community.model.ResultJson;
import com.community.model.Question;
import com.community.model.User;
import com.community.services.QuestionService;
import com.community.services.UserService;
import com.community.utils.UserUntils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    //light:OK
    @PostMapping("pushQuestion")
    public ResultJson<Question> createQuestion(
            HttpServletRequest request,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tags") String[] tags) {
        User token = UserUntils.checkUser(request);
        ResultJson<Question> json = null;
        Question question = null;
        if (token == null) {
            json = new ResultJson(ErrorEnum.CHECK_USER_LOGIN);
        } else {
            json = new ResultJson<>(SucessEnum.CREATE_QUESTION);
            question = new Question();
            question.setTag(TagMap.getTagSum(tags));
            question.setTitle(title);
            question.setDescription(description);
            question.setCreator(token.getAccountId());
            questionService.createUpdateQuestion(question);
        }
        json.setData(question);
        return json;
    }
    //light:OK
    @GetMapping("question/{Id}")
    public ResultJson<QuestionDTO> lookQuestion(
            @PathVariable("Id") Long id) {
        Question question = questionService.selectQuestion(id);
        Assert.notNull(question,"找不到这个文章");
        questionService.addView(id);

        return new ResultJson<QuestionDTO>(10001, "查找成功",new QuestionDTO(question,
                new UserDTO(userService.getUser(question.getCreator()))
        ), null);
    }
    //light:OK
    @GetMapping("questionTag")
    public ResultJson<String> getTags() {
        ResultJson<String> json = new ResultJson<>(10001, "获取标签成功");
        json.setDatas(TagMap.getTags());
        return json;
    }
    //light:OK
    @PostMapping("question")
    public ResultJson<QuestionDTO> question(
            @RequestBody @NotNull SearchDTO model
    ) {
        System.out.println(model);
        ResultJson<QuestionDTO> json = new ResultJson<>(SucessEnum.QUESTION_SEARCH);
        Long tag = TagMap.getTagSum(model.getTag());
        PageInfo<QuestionDTO> pageQuestion = questionService.getPageQuestion(model.getSort(), tag, model.getSearch(), model.getPageSize(), model.getPageIndex());
        json.setDatas(pageQuestion.getList());
        return json;
    }
    //light:OK
    @PutMapping("question/{questionId}")
    public ResultJson addQuestionLike(
            @PathVariable("questionId")  @Valid @NotNull Long questionId
    ){
        questionService.addLike(questionId);
        return new ResultJson(SucessEnum.ADD_LIKE);
    }
    //light:OK
    @DeleteMapping("question")
    public ResultJson delquestion(
        @RequestBody @NotNull(message ="校验失败") @Size(min=1,message = "校验失败") List<Long> listId
    ){
        questionService.delQuestionList(listId);
        return new ResultJson(SucessEnum.DEL_COMMENT);
    }
}
