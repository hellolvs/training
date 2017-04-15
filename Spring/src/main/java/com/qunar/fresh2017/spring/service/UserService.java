package com.qunar.fresh2017.spring.service;

import com.qunar.fresh2017.spring.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/15.
 */
@Service
public interface UserService {

    public int removeById(int id);

    public User findById(int id);

    public List<User> findAll();

    public List<User> findAll(RowBounds rowBounds);

    public User save(User user);

    public User update(User user) ;
}
