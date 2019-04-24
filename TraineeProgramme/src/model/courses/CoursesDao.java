package model.courses;

import java.time.Year;
import java.util.Set;

public interface CoursesDao {
    Courses getCourses(int id);
    Set<Courses> getAllCourses();
    Courses getCoursesByNameAndYear(String name, Year year);
    boolean insertCourses(Courses courses);
    boolean updateCourses(Courses courses);
    boolean deleteCourses(int id);
}
