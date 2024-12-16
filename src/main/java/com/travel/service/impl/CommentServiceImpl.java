package com.travel.service.impl;

import com.travel.entity.*;
import com.travel.mapper.LikeMapper;
import com.travel.mapper.ReportMapper;
import com.travel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travel.mapper.CommentMapper;

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
        criteria.andStatusEqualTo(1);
        return commentMapper.selectByExample(example);
    }


    @Override
    public int deleteCommentById(Long cid) {
            try {
                commentMapper.deleteByPrimaryKey(cid);
                return 1;
            } catch (Exception e) {
                // 处理异常
                e.printStackTrace();
                return -1;
            }
    }





    @Override
    public int likeComment(Long cid, Long uid) {
        //随机生成lid，如果已存在，重新生成
        Long lid = (long) (Math.random() * 1000000000);
        while (likeMapper.selectByPrimaryKey(lid) != null) {
            lid = (long) (Math.random() * 1000000000);
        }
        Like like = new Like(lid,uid, cid, "comment",new Date(), true);
        try {
            likeMapper.insertSelective(like);
            Comment comment = commentMapper.selectByPrimaryKey(cid);
            String type = "comment";
            int result = commentMapper.updateLikeCount(cid,type);
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

    /**
     * 举报评论
     * @param reporter
     * @param reported
     * @param type
     * @param category
     * @param description
     * @return
     */
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

    /**
     * 更新评论
     * @param comment
     * @return
     */
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

    /**
     * 删除用户的所有评论
     * @param uid
     * @return
     */
    public boolean deleteAllCommentByUid(Long uid) {
        try {
            commentMapper.deleteAllCommentByUid(uid);
            return true;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return false;
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

    public User getUserByUid(Long uid) {
        try {
            return commentMapper.getUserByUid(uid);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }

    public int unLikeComment(Long cid, Long uid) {
        //随机生成lid，如果已存在，重新生成
        Long lid = (long) (Math.random() * 1000000000);
        while (likeMapper.selectByPrimaryKey(lid) != null) {
            lid = (long) (Math.random() * 1000000000);
        }

//        Like like = new Like(lid,uid, cid, "comment",new Date(), true);
        LikeExample like = new LikeExample();
        LikeExample.Criteria criteria = like.createCriteria();
        criteria.andConnectidEqualTo(cid);
        criteria.andUidEqualTo(uid);
        criteria.andTypeEqualTo("comment");


        try {
            likeMapper.deleteByExample(like);
            Comment comment = commentMapper.selectByPrimaryKey(cid);
            String type = "comment";
            int result = commentMapper.updateLikeCount(cid,type);
            return 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return -1;
        }
    }

    public List<Comment> getCommentsByAssociatedIdandAssociatedType(Long connectid, String type) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();

        criteria.andConnectidEqualTo(connectid);
        criteria.andTypeEqualTo(type);
        criteria.andStatusEqualTo(1);
        return commentMapper.selectByExample(example);
    }
}