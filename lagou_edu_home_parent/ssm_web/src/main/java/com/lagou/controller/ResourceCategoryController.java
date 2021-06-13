package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;



    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        return  new ResponseResult(true,200,"查询所有分类信息成功",allResourceCategory);

    }



    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory ){
        //判断是否携带了章节ID
        if(resourceCategory.getId()==null){
            //新增
            resourceCategoryService.saveResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增资源分类成功", null);
            return responseResult;
        }else {
            //更新
            resourceCategoryService.updateResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新资源分类成功", null);
            return responseResult;
        }

    }

    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
        return  new ResponseResult(true,200,"删除资源分类成功",null);
    }



}
