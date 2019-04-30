package model.course;

import model.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseDaoImpl implements CourseDao {

    private Course extractCoursesFromResult(ResultSet rs) throws SQLException {
        return new Course(rs.getInt("CourseID"),
                rs.getDate("CourseStartDate").toLocalDate(),
                rs.getDate("CourseEndDate").toLocalDate(),
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
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
    public Set<Course> getCoursesByName(String name) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses where CourseName =" + name );
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO courses VALUES (NULL,?, ?, ?, ?,?)");
            ps.setString(1, course.getCourseName());
            ps.setDate(2, Date.valueOf(course.getStartDate()));
            ps.setDate(3, Date.valueOf(course.getEndDate()));
            ps.setString(4, course.getCourseRoom());
            ps.setString(5, course.getCourseDescription());
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
            PreparedStatement ps = conn.prepareStatement("UPDATE Courses SET CourseStartDate=?,CourseEndDate=?,  CourseName=?, CourseDescription=? , CourseRoom=? WHERE CourseID=?");

            ps.setDate(1,Date.valueOf(course.getStartDate()));
            ps.setDate(2, Date.valueOf(course.getEndDate()));
            ps.setString(3, course.getCourseName());
            ps.setString(4, course.getCourseDescription());
            ps.setString(5, course.getCourseRoom());
            ps.setInt(6, course.getCourseID());
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

    @Override
    public List<String> getAllRoomsValues(){
        Connection conn = ConnectionFactory.getConnection();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM trainee_programm_db.courses LIKE 'CourseRoom'");
            List<String> availableEnums = new ArrayList<>();
            while (rs.next()) {
                String enums = rs.getString("Type");
                System.out.println(enums);
                int position = 0, count = 0;


                while (( position = enums.indexOf("'", position) ) > 0) {
                    int secondPosition = enums.indexOf("'", position + 1);
                    availableEnums.add(enums.substring(position + 1, secondPosition));

                    //availableEnums[count++] = enums.substring(position + 1, secondPosition);

                    position = secondPosition + 1;

                }
            }
            return availableEnums;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


}

















