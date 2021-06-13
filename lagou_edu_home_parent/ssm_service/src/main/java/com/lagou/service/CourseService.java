package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    /*
    *
    * 多条件课程列表查询
    * */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
    * 添加课程和讲师信息
    * */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    * 回显
    * */
    public CourseVO findCourseById(Integer id);
/*
* 更新
* */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    * 修改课程状态
    * */
    public void updateCourseStatus(int courseId,int status);

    /*
    *
    * */

}
