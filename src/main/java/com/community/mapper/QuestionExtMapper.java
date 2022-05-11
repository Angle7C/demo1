package com.community.mapper;

import com.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionExtMapper {
    int addView(Long Id);
    int addComment(Long Id);
    int addLike(Long Id);
}
