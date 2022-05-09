package com.community.mapper;

import com.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionExtMapper {
    int addView(Integer Id);
    int addComment(Integer Id);
    int addLike(Integer Id);
}
