package com.community.services;

import com.community.dto.CommentDTO;
import com.community.dto.QuestionDTO;
import com.community.dto.UserDTO;
import com.community.enums.ErrorEnum;
import com.community.execption.ServiceExecption;
import com.community.mapper.CommentExtMapper;
import com.community.mapper.CommentMapper;
import com.community.mapper.UserMapper;
import com.community.model.Comment;
import com.community.model.CommentExample;
import com.community.model.ResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService  {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Transactional
    public int  addLike(Long commentId) {
        return commentExtMapper.addLike(commentId);
    }
    @Transactional
    public  int updateOrCreate(Comment comment) {
        if(comment.getId()==null){
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setGmtModified(comment.getGmtCreate());
             return commentMapper.insert(comment);
        }else{
            comment.setGmtModified(System.currentTimeMillis());
            return commentMapper.updateByPrimaryKey(comment);
        }
    }

    public PageInfo<CommentDTO> findforQuestion(Integer pageSize, Integer pageIndex, Long questionId){
        PageHelper.startPage(pageIndex,pageSize);
        CommentExample example=new CommentExample();
        example.createCriteria().andQuestionIdEqualTo(questionId);
        List<Comment> comments = commentMapper.selectByExample(example);
        Assert.notEmpty(comments,"没有查到评论");
        List<CommentDTO> list = comments.stream().map(item -> {
            return new CommentDTO(item,
                    new UserDTO(userMapper.selectByPrimaryKey(item.getCreateId())));
        }).collect(Collectors.toList());
        PageInfo<CommentDTO> pageInfo=new PageInfo<>(list);
        return  pageInfo;
    }
    @Transactional
    public int delCommentList(List<Long> listID) {
        CommentExample example=new CommentExample();
        example.createCriteria().andIdIn(listID);
        int i = commentMapper.deleteByExample(example);
        Assert.isTrue(i==listID.size(),()->{
            throw new ServiceExecption(ErrorEnum.SERVICE_NUM_ERROR);
        });
        return i;
    }

}
