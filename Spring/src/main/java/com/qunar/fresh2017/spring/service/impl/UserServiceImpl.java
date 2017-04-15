package com.qunar.fresh2017.spring.service.impl;

import com.qunar.fresh2017.spring.mapper.UserMapper;
import com.qunar.fresh2017.spring.model.User;
import com.qunar.fresh2017.spring.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/15.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int removeById(int id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findAll(RowBounds rowBounds) {
        return userMapper.findAll(rowBounds);
    }

    @Override
    public User save(User user){
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        userMapper.updateByPrimaryKey(user);
        return user;
    }
}
