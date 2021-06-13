package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    /*
    *
    * 查询所有角色
    * */
    public List<Role> findAllRole(Role role);


    /*
     *根据角色Id查询该角色关联的菜单信息ID
     * */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
    *
    * 为角色分配菜单
    * */
    public void roleContextMenu(RoleMenuVo roleMenuVo);


    /*
    * 删除角色
    * */
    public void deleteRole(Integer roleid);

    /*
     *根据角色Id查询该角色关联的资源信息ID
     * */
    public List<ResourceCategory> findResourceByRoleId(Integer roleId);


    public void roleContextResource(RoleResourceVo roleResourceVo);
}
