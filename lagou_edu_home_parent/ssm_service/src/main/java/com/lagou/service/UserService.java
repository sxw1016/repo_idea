package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;

public interface UserService {
    /*
     * 分页
     * */
    public PageInfo findAllUserByPage(UserVO userVO);

    /*
     *
     * 用户登录*/
    public User login(User user) throws Exception;

    /*
     *
     * 根据用户Id查询关联的角色信息*/
    public List<Role> findUserRelationRoleById(Integer id);

    /*
           用户关联角色
        */
    public void userContextRole(UserVO userVO);


    /*
     *
     * 获取用户权限，进行菜单动态展示*/
    public ResponseResult getUserPermissions(Integer userid);

}