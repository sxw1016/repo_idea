package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    //根据ID查询对应的章节加课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);
    //回显章节对应的课程信息
    public Course findCourseByCourseId(int courseId);

    public void saveSection(CourseSection courseSection);

    public void updateSection(CourseSection courseSection);
    public void updateSectionStatus(int id,int status);
}
