package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);
    /*
     * 回显章节对应的课程信息
     * */
    public Course findCourseByCourseId(Integer courseId);

    /*
    * 新增章节信息
    *
    * */
    public void saveSection(CourseSection courseSection);

    /*
    * 更新章节信息
    * */
    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     * */
    public void updateSectionStatus(CourseSection section);
}
