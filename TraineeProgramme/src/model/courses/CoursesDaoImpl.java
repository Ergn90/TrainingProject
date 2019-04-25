package model.courses;

import java.time.Year;
import java.util.Set;

public class CoursesDaoImpl implements CoursesDao {
    @Override
    public Courses getCourses(int id) {
        return null;
    }

    @Override
    public Set<Courses> getAllCourses() {
        return null;
    }

    @Override
    public Courses getCoursesByNameAndYear(String name, Year year) {
        return null;
    }

    @Override
    public boolean insertCourses(Courses courses) {
        return false;
    }

    @Override
    public boolean updateCourses(Courses courses) {
        return false;
    }

    @Override
    public boolean deleteCourses(int id) {
        return false;
    }
}
