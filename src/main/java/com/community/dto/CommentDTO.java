package com.community.dto;

import com.community.model.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    //创建者
    private UserDTO userDTO;
    //问题id
    private Long questionId;
    //描述
    private String commentator;
    //点赞
    private Long likeCount;

    public Comment toModel( ){
        Comment comment=new Comment();
        comment.setCommentator(this.commentator);
        comment.setQuestionId(this.questionId);
        comment.setLikeCount(this.likeCount);
        if(this.userDTO!=null)
            comment.setCreateId(this.userDTO.getAccountId());
        else
            comment.setCreateId(null);
        comment.setId(this.id);
        return comment;
    }
    public CommentDTO(Comment comment, UserDTO userDTO){
        this.commentator=comment.getCommentator();
        this.id=comment.getId();
        this.likeCount=comment.getLikeCount();
        this.questionId=comment.getQuestionId();
        this.userDTO=userDTO;
    }
}
