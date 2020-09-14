package com.winterchen.dao;


import com.winterchen.model.User;
import com.winterchen.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserDao {



    List<UserDomain> selectUsers();

    void addUser(User user);

    List<User> getUsersByUser(User userQuery);

    void updateById(User user);

    void deleteByIds(List<Integer> ids);

    void editStatus(List<Integer> ids, String status);
}