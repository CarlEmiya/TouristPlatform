package com.travel.service.impl;

import com.travel.entity.CommentExample;
import com.travel.entity.Like;
import com.travel.entity.Report;
import com.travel.mapper.LikeMapper;
import com.travel.mapper.ReportMapper;
import com.travel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travel.mapper.CommentMapper;
import com.travel.entity.Comment;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private LikeMapper likeMapper;

    /**
     * 这有个AssociatedType,准备在前端传过来
     *
     * @param comment
     * @return
     */
    @Override
    public int addComment(Comment comment) {
        comment.setCreated(new Date());
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
    public List<Comment> getCommentsByAssociatedIdAndUidandAssociatedType(Long connectid, Long uid, String type) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();

        criteria.andConnectidEqualTo(connectid);
        criteria.andUidEqualTo(uid);
        criteria.andTypeEqualTo(type);
        return commentMapper.selectByExample(example);
    }


    @Override
    public int deleteCommentById(Long cid) {
        Comment comment = commentMapper.selectByPrimaryKey(cid);
        if (comment!= null) {
            comment.setCid(cid);
            comment.setDeleted(new Date());
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
    public int likeComment(Long cid, Long uid) {
        Like like = new Like(uid, cid, "comment",new Date(), true);
        try {
            likeMapper.insertSelective(like);
            return 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public int getCommentAgreeCount(Long cid) {
        Comment comment = commentMapper.selectByPrimaryKey(cid);
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

    @Override
    public int reportComment(Long reporter, Long reported, String type, String category,  String description) {
        //随机生成rid，如果已存在，重新生成
        Long rid = (long) (Math.random() * 1000000000);
        while (commentMapper.selectByPrimaryKey(rid) != null) {
            rid = (long) (Math.random() * 1000000000);
        }

        Report report = new Report(rid, reporter, reported, type, category, new Date(), 12, null, null, description);
        try {
            reportMapper.insert(report);
            return 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return -1;
        }
    }

    public int updateComment(Comment comment) {
        try {
            commentMapper.updateByPrimaryKeySelective(comment);
            return 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteAllCommentByUid(Long uid) {
        try {
            commentMapper.deleteAllCommentByUid(uid);
            return 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return -1;
        }
    }

    public List<Comment> getCommentsByUid(Long uid) {
        try {
            CommentExample example = new CommentExample();
            CommentExample.Criteria criteria = example.createCriteria();
            criteria.andUidEqualTo(uid);
            return commentMapper.selectByExampleWithBLOBs(example);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }
}