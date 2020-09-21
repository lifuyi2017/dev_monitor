package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.User;
import com.winterchen.model.UserDomain;
import com.winterchen.model.UserRequest;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface UserService {

  

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

    void addUser(User user) throws Exception;

    PageInfo<User> getUsersByUser(UserRequest userRequest) throws Exception;

    void updateById(User user) throws Exception;

    void deleteByIds(List<Integer> ids) throws Exception;

    void editStatus(List<Integer> ids, String status) throws Exception;

    List<User> getUsersByUserNoPage(User userQuery);
}
