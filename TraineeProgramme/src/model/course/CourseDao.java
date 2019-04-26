package model.course;

import java.util.Set;

public interface CourseDao {
    Course getCourse(int id);
    Set<Course> getAllCourses();
    Set<Course> getCoursesByName(String name);
    boolean insertCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
}
