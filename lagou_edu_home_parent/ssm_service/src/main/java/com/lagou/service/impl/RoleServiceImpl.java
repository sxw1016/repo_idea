package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //1.清空中间表的关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2.为角色分配菜单信息

        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {
        //调用根据roleid清空中间表的方法
        roleMapper.deleteRoleContextMenu(roleid);
        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceByRoleId(Integer roleId) {
        List<ResourceCategory> resourceByRoleId = roleMapper.findResourceByRoleId(roleId);
        return resourceByRoleId;

    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {
        //1.根据RoleiD清空中间表关系
        roleMapper.deleteRoleContextMenu(roleResourceVo.getRoleId());
        //2.重新建立
        for (Integer resourceId : roleResourceVo.getResourceIdList()) {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setResourceId(resourceId);
            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);

            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
            roleMapper.roleContextResource(roleResourceRelation);


        }
    }
}
