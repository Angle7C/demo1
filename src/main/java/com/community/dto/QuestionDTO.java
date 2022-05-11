package com.community.dto;

import com.community.hander.TagMap;
import com.community.model.Comment;
import com.community.model.Question;
import com.community.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String[] tags;
    private UserDTO user;
    //浏览的数量
    private Integer viewCount;
    //评论的数量
    private Integer commentCount;
    //收藏的数量
    private Integer likeCount;

    public Question toModel( ){
        Question question=new Question();
        question.setTag(TagMap.getTagSum(tags));
        question.setDescription(this.getDescription());
        question.setTitle(title);
        question.setCreator(user.getAccountId());
        question.setCommentCount(commentCount);
        question.setViewCount(viewCount);
        question.setLikeCount(likeCount);
         question.setId(id);
        return question;
    }
    public QuestionDTO(Question question, UserDTO userDTO){
        this.setViewCount(question.getViewCount());
        this.setDescription(question.getDescription());
        this.setUser(userDTO);
        this.setTitle(question.getTitle());
        this.setLikeCount(question.getLikeCount());
        setTags(TagMap.changeString(question.getTag()));
        this.setId(question.getId());
        this.setCommentCount(question.getCommentCount());
    }
    
}