package com.qdu.service.impl;

import com.qdu.entity.CommentExample;
import org.springframework.stereotype.Service;
import com.qdu.mapper.CommentMapper;
import com.qdu.entity.Comment;
import com.qdu.service.CommentService;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

//    @Autowired
    private CommentMapper commentMapper;

    /**
     * 这有个AssociatedType,准备在前端传过来
     *
     * @param comment
     * @return
     */
    @Override
    public int addComment(Comment comment) {
        comment.setCreatedAt(new Date());
        comment.setStatus(1); // 设置为正常状态
        comment.setAgree(0);

        commentMapper.insert(comment);
        try {
            return 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Comment> getCommentsByAssociatedIdAndUserIdandAssociatedType(String associatedId, String userId, String associatedType) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();

        criteria.andAssociatedidEqualTo(associatedId);
        criteria.andUseridEqualTo(userId);
        criteria.andAssociatedtypeEqualTo(associatedType);
        return commentMapper.selectByExample(example);
    }


    @Override
    public int deleteCommentById(String commentId) {
        Comment comment = commentMapper.findById(commentId);
        if (comment!= null) {
            comment.setCommentId(commentId);
            comment.setDeletedAt(new Date());
            comment.setStatus(3); // 设置为预删除状态
            try {
                commentMapper.updateByPrimaryKey(comment);
                return 1;
            } catch (Exception e) {
                // 处理异常
                e.printStackTrace();
                return -1;
            }
        } else {
            // 评论不存在
            return 0;
        }
    }

    @Override
    public int likeComment(String commentId) {
        Comment comment = commentMapper.findById(commentId);
        if (comment!= null) {
            try {
                comment.setAgree(comment.getAgree() + 1);
                return 1;
            } catch (Exception e) {
                // 处理异常
                e.printStackTrace();
                return -1;
            }
        } else {
            // 评论不存在
            return 0;
        }
    }

    @Override
    public int getCommentAgreeCount(String commentId) {
        Comment comment = commentMapper.findById(commentId);
        if (comment!= null) {
            try {
                return comment.getAgree();
            } catch (Exception e) {
                // 处理异常
                e.printStackTrace();
                return -1;
            }
        } else {
            // 评论不存在
            return 0;
        }
    }
}