package com.community.services;

import com.community.dto.QuestionDTO;
import com.community.hander.TagMap;
import com.community.mapper.QuestionExtMapper;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Question;
import com.community.model.QuestionExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Setter
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    //light:OK
    public PageInfo<QuestionDTO> getPageQuestion(String sort, Long tag, String search, Integer pageSize, Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        QuestionExample questionExample = new QuestionExample();
        //light: 排序
        if (sort != null)
            questionExample.setOrderByClause(sort);
        //light: 模糊查询
        QuestionExample.Criteria criteria = questionExample.createCriteria().andTitleLike("%" + search + "%");
        //light: 标签查询
        if (tag != 0L && tag != null)
            criteria.andTagEqualTo(tag);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        List<QuestionDTO> collect = questions.stream().map(item -> {
            return item.getDTO(userMapper.selectByPrimaryKey(item.getCreator()).getDTO());
        }).collect(Collectors.toList());
        PageInfo<QuestionDTO> info = new PageInfo<>(collect);
        return info;
    }

    public List<Question> selectQuestion(String search, String[] tag, String sort) {
        QuestionExample questionExample = new QuestionExample();
        if (sort != null) questionExample.setOrderByClause(sort);
        Long tags = TagMap.getTagSum(tag);
        questionExample.createCriteria().andTitleLike(search).andTagLessThan(tags);
        return questionMapper.selectByExample(questionExample);
    }

    public int createUpdateQuestion(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            return questionMapper.insert(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            return questionMapper.updateByPrimaryKey(question);
        }
    }

    public Question selectQuestion(Integer id) {
        Assert.notNull(id, "id不能为空");
        Question question = questionMapper.selectByPrimaryKey(id);
        questionExtMapper.addView(id);
        return question;
    }

    public int addView(Integer id) {
        return questionExtMapper.addView(id);
    }
    public int addView(Question question){
        return questionExtMapper.addView(question.getId());

    }
}
