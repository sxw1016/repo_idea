package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /*
    * 用户分页 多条件组合查询
    * */
    public List<User> findAllUserByPage(UserVO userVO);

    /*
    *
    * 用户登录
    * */
        public User  login(User user);


        /*
        *
        * 根据用户ID清空中间表*/
        public void deleteUserContextRole(Integer userId);

        /*
        *
        * 分配角色*/
        public void userContextRole(User_Role_relation user_role_relation);

        /*
         *
         * 1.根据用户Id查询关联的角色信息*/
        public List<Role> findUserRelationRoleById(Integer id);


        /*
        *
        *2.根据角色ID，查询角色拥有的顶级菜单
        * */
        public List<Menu> findParentMenuByRoleId(List<Integer> ids);


        /*
        * 3.根据PID，查询子菜单信息
        * */
        public List<Menu> findSubMenuByPid(Integer pid);


        /*
        * 4.获取用户拥有的资源权限信息
        * */
        public List<Resource> findResourceByRoleId(List<Integer> ids);

  public List<Resource> findResourceByRoleId2(List<Integer> ids);


}
