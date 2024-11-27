package com.qdu.service.impl;

import com.qdu.mapper.UserMapper;
import com.qdu.entity.User;
import com.qdu.entity.UserExample;
import com.qdu.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        try {

            //先随机生成uid，检测一下是否已经存在，存在就重新生成
            Long uid;
            do {
                uid = (long) (Math.random() * 10000000000000000L);
            } while (userMapper.selectByPrimaryKey(uid)!= null);
            user.setUid(uid);

            user.setCreated(new Date());
            user.setGender("F");
            user.setBirth(new Date());
            user.setLevel(1);
            user.setStatus(1);
            String encryptedPassword = DigestUtils.md5Hex(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setIdentify(0);
//            user.setIdCard("123456789012345678");
//            user.setCity("北京市");
//            user.setOccupation("学生");
//            user.setRecoveryQuestion("喜欢爸爸还是妈妈?");
//            user.setAnswer("妈妈");
//            user.setDeletedAt(null);
//            user.setDeletionPeriod(365);
            user.setPicture("static/img/profilePicture/10002.jpeg");
            user.setDescription("这个人很懒，什么都没留下。");
            System.out.println(user);
            userMapper.insertSelective(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public User loginUser(Long uid, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(uid);

        String encryptedPassword = DigestUtils.md5Hex(password);
        criteria.andPasswordEqualTo(encryptedPassword);

        try {
            List<User> users = userMapper.selectByExample(example);

            if (users!= null &&!users.isEmpty()) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> searchUsers(UserExample example) {
        try {
            return userMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateUser(User user) {
        try {
            userMapper.updateByPrimaryKey(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateUserLevel(Long uid, int newLevel) {
        try {
            User user = userMapper.selectByPrimaryKey(uid);
            if (user!= null) {
                user.setLevel(newLevel);
                return updateUser(user);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateUserStatus(Long uid, int newStatus) {
        try {
            User user = userMapper.selectByPrimaryKey(uid);
            if (user!= null) {
                user.setStatus(newStatus);
                return updateUser(user);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int disableUser(Long uid) {
        try {
            return updateUserStatus(uid, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteUser(Long uid) {
        try {
            userMapper.deleteByPrimaryKey(uid);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isCreatedByExistingAdmin(User admin) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(admin.getUid());
        criteria.andIdentifyEqualTo(1);
        try {
            List<User> users = userMapper.selectByExample(example);
            return !users.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}