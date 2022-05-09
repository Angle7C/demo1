package com.community.dto;

import com.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String[] tags;
    private UserDTO user;
    //创建者的ID

    //浏览的数量
    private Integer viewCount;
    //评论的数量
    private Integer commentCount;
    //收藏的数量
    private Integer likeCount;

    private Integer sticky;
}