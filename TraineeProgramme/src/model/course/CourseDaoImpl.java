package model.course;

import model.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CourseDaoImpl implements CourseDao {

    private Course extractCoursesFromResult(ResultSet rs) throws SQLException {
        return new Course(rs.getInt("CourseID"),
                rs.getDate("CourseDate"),
                rs.getString("CourseName"),
                rs.getString("CourseRoom"),
                rs.getString("CourseDescription"));
    }

    @Override
    public Course getCourse(int id) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses where CourseID =" + id);

            if (rs.next()) {
                return extractCoursesFromResult(rs);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return null;
    }

    @Override
    public Set<Course> getAllCourses() {
        Connection connection = ConnectionFactory.getConnection();
        Set<Course> coursesList = new HashSet<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
            while (resultSet.next()) {
                Course course = extractCoursesFromResult(resultSet);
                coursesList.add(course);
            }
            return coursesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursesList;
    }


    @Override
    public Set<Course> getCoursesByNameAndDate(String name, java.util.Date date) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses where CourseName =" + name + " and CourseDate=" + new java.sql.Date(date.getTime()));
            Set<Course> coursesSet = new HashSet();
            while (rs.next()) {
                Course course = extractCoursesFromResult(rs);
                coursesSet.add(course);
            }
            return coursesSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertCourse(Course course) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Courses VALUES (NULL,?, ?, ?, ?)");
            ps.setDate(1, new java.sql.Date(course.getStartDate().getTime()));
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getCourseDescription());
            ps.setString(4, course.getCourseRoom());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Courses SET CourseDate=?, CourseName=?, CourseDescription=? , CourseRoom=? WHERE CourseID=?");

            ps.setDate(2, new java.sql.Date(course.getStartDate().getTime()));
            ps.setString(3, course.getCourseName());
            ps.setString(4, course.getCourseDescription());
            ps.setString(5, course.getCourseRoom());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Courses WHERE CourseID=" + id);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }


}

















