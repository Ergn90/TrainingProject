package model.courses;

import java.sql.SQLException;
import java.time.Year;
import java.util.Date;
import java.util.Set;

public interface CoursesDao {
    Courses getCourses(int id);
    Set<Courses> getAllCourses();
    Set<Courses> getCoursesByNameAndDate(String name, Date date);
    boolean insertCourses(Courses courses);
    boolean updateCourses(Courses courses);
    boolean deleteCourses(int id);
}
