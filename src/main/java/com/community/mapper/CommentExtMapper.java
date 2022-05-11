package com.community.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentExtMapper {
    int addLike(Long id);
}
