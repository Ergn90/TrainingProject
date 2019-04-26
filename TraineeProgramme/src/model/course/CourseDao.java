package model.course;

import java.util.Date;
import java.util.Set;

public interface CourseDao {
    Course getCourse(int id);
    Set<Course> getAllCourses();
    Set<Course> getCoursesByNameAndDate(String name, Date date);
    boolean insertCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
}
