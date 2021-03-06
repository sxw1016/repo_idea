package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        用户分页&多条件组合查询方法
     */

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){

        PageInfo pageInfo = userService.findAllUserByPage(userVO);

        return  new ResponseResult(true,200,"分页多条件查询成功",pageInfo);

    }

    /*
    *
    * 登录*/
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user1 = userService.login(user);
        if(user1!=null){

            //保存用户id access_token 到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            //将查询出来的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            //将查询出来的user也存到
            map.put("user",user1);
            return new ResponseResult(true,1,"登录成功",map);




        }else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }

    }
    /*
    *
    * 分配角色回显*/
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",roleList);


    }
    /*
    *
    * 分配角色*/
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){

        userService.userContextRole(userVO);
        return new ResponseResult(true,200,"分配角色成功",null);


    }

    /*
    *
    * 获取用户权限进行菜单动态展示*/
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
//获取请求头中的 token
        String token = request.getHeader("Authorization");
//获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String)session.getAttribute("access_token");
//判断
        if(token.equals(access_token)){
            int user_id = (Integer)session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else{
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;

        }
    }

}





