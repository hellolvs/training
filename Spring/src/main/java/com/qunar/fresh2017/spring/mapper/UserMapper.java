package com.qunar.fresh2017.spring.mapper;

import com.qunar.fresh2017.spring.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/15.
 */
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(int id);

    User selectByPrimaryKey(int id);

    List<User> findAll();

    List<User> findAll(RowBounds rowBounds);

    int insert(User user);

    int updateByPrimaryKey(User user);
}
