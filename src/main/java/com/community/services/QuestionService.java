package com.community.services;
import com.community.mapper.QuestionMapper;
import com.community.model.Question;
import com.community.model.QuestionExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class QuestionService {
    @Setter
    @Autowired
    private QuestionMapper questionMapper;
    public PageInfo<Question> getPageQuestion(String sort, String tag,String search ,Integer pageSize,Integer pageIndex){
        String[] searchs = search.split(" ");
        String[] tags=tag.split(" ");
        QuestionExample questionExample=new QuestionExample();
        QuestionExample.Criteria criteria = questionExample.createCriteria();
        String handerSearch="";

        if(searchs!=null){
             Arrays.stream(searchs)
                    .map(s -> {
                      return   s.replace("+", "")
                                .replace("*", "")
                                .replace("?", "");
                    }).forEach(item->{
                        criteria.andTitleLike(item);
                    });
        }
        if(tags!=null){
            AtomicReference<Long> tagValue= new AtomicReference<>(0L);
            Arrays.stream(tags).parallel().forEach(item->{
//                tagValue.updateAndGet(v -> v | TagMap.getTagCode(item));
            });
            criteria.andTagEqualTo(tagValue.get());
        }
        if(sort!=null){
            questionExample.setOrderByClause(sort.replace("1","ASC").replace("0","DESC"));
        }

        PageHelper.startPage(pageIndex,pageSize);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        PageInfo<Question> info = PageInfo.of(questions);
        return info;
    }
    public int createUpdateQuestion(Question question){
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            question.setViewCount(0);
            question.setLikeCount(0);
            return questionMapper.insert(question);
        }
        else{
            question.setGmtModified(System.currentTimeMillis());
            return questionMapper.updateByPrimaryKey(question);
        }
    }
}
