package model.courses;

import model.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CoursesDaoImpl implements CoursesDao {

    private Courses extractCoursesFromResult(ResultSet rs) throws SQLException {
        return new Courses(rs.getInt("CourseID"),
                rs.getDate("CourseDate"),
                rs.getString("CourseName"),
                rs.getString("CourseRoom"),
                rs.getString("CourseDescription"));
    }

    @Override
    public Courses getCourses(int id) {
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
    public Set<Courses> getAllCourses() {
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
            Set<Courses> coursesList = new HashSet<>();
            while (resultSet.next()) {
                Courses courses = extractCoursesFromResult(resultSet);
                coursesList.add(courses);
            }
            return coursesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Set<Courses> getCoursesByNameAndDate(String name, java.util.Date date) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses where CourseName =" + name + " and CourseDate=" + new java.sql.Date(date.getTime()));
            Set<Courses> courses = new HashSet();
            while (rs.next()) {
                Courses course = extractCoursesFromResult(rs);
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertCourses(Courses courses) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Courses VALUES (NULL,?, ?, ?, ?)");
            ps.setDate(1, new java.sql.Date(courses.getStartDate().getTime()));
            ps.setString(2, courses.getCourseName());
            ps.setString(3, courses.getCourseDescription());
            ps.setString(4, courses.getCourseRoom());
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
    public boolean updateCourses(Courses courses) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Courses SET CourseDate=?, CourseName=?, CourseDescription=? , CourseRoom=? WHERE CourseID=?");

            ps.setDate(2, new java.sql.Date(courses.getStartDate().getTime()));
            ps.setString(3, courses.getCourseName());
            ps.setString(4, courses.getCourseDescription());
            ps.setString(5, courses.getCourseRoom());
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
    public boolean deleteCourses(int id) {
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

















