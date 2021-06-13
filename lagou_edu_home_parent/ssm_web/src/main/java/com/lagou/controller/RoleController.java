package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    /*
    * 查询所有角色
    *
    * */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);
        return  responseResult;


    }


    /*
    * 查询所有的父子菜单信息
    * */
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){

        List<Menu> menuList = menuService.findSubMenuListByPid(-1);//-1查询所有的父级菜单
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        return  new ResponseResult(true,200,"查询所有的父子菜单信息成功",map);


    }


    /*
    *
    * 根据角色ID查询关联菜单ID*/
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"查询角色关联的菜单信息成功",menuByRoleId);


    }

    /*
    * 为角色分配菜单
    *
    * */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true, 200, "响应成功", null);
    }
//删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功",null);

    }
    /*
     *
     * 根据角色ID查询关联资源ID*/
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> list = roleService.findResourceByRoleId(roleId);
        return new ResponseResult(true,200,"查询角色关联的资源信息成功",list);

    }
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody  RoleResourceVo roleResourceVo){
        roleService.roleContextResource(roleResourceVo);
        return  new ResponseResult(true,200,"分配资源成功",null);

    }



}
