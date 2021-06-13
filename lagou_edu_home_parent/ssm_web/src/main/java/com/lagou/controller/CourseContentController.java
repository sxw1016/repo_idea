package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){
        //调用service
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "章节及内容查询成功", list);
        return  responseResult;

    }
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult  responseResult= new ResponseResult(true, 200, "查询课程信息成功", course);
        return  responseResult;

    }

    //新增或者更新章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        //判断是否携带了章节ID
        if(courseSection.getId()==null){
            //新增
            courseContentService.saveSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增章节成功", null);
            return responseResult;
        }else {
            //更新
            courseContentService.updateSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新章节成功", null);
            return responseResult;
        }

    }
    /**
     * 修改章节状态
     * 状态，0:隐藏；1：待更新；2：已发布
     * */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id,@RequestParam
            int status){
        try {
            courseContentService.updateSectionStatus(
                    id,status);
//封装最新的状态信息
            Map<String,Integer> map = new HashMap<>();
            map.put("status",status);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
