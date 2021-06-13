package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    /*
     * 查询所有资源分类
     * */
    public List<ResourceCategory> findAllResourceCategory();
    /*
     *添加资源分类
     * */
    public void saveResourceCategory(ResourceCategory resourceCategory);
    /*
     * 更新章节分类
     * */
    public void updateResourceCategory(ResourceCategory resourceCategory);
    //  删除资源分类根据ID
    public   void  deleteResourceCategory(Integer id);
}
