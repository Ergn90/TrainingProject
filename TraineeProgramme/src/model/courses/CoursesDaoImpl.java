package model.courses;

import model.ConnectionFactory;

import java.sql.*;
import java.time.Year;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CoursesDaoImpl implements CoursesDao {

    @Override
    public Courses getCourses(int id) {
        Courses courses = null;
        Iterator it = getSetOfCourses("SELECT * FROM Courses where CourseID = " + id).iterator();
        while (it.hasNext()) {
            courses = (Courses) it.next();
        }
        return courses;
    }

    @Override
    public Set<Courses> getAllCourses() {
        return getSetOfCourses("SELECT * FROM Courses;");
    }

    @Override
    public Set<Courses> getCoursesByNameAndYear(String name, Year year) {
        return getSetOfCourses("SELECT * FROM Courses where CourseName = '" + name + "' and CourseYear = '" + year + "';");
    }

    @Override
    public boolean insertCourses(Courses courses){
        /*Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Courses VALUES (NULL,?, ?, ?, ?)");
            ps.setDate(2, new java.sql.Date(courses.getCourseYear().getTime()));
            ps.setInt(3, courses.getCourseName());
            ps.setString(4, courses.getCourseDescription());
            ps.setString(5, courses.getCourseRoom());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;*/
        return  isCourseChanged(courses,"INSERT INTO Courses VALUES (NULL,?, ?, ?, ?)");
    }

    @Override
    public boolean updateCourses(Courses courses) {
        /*try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Courses SET CourseYear=?, CourseName=?, CourseDescription=? , CourseRoom=? WHERE CourseID=?");

            ps.setDate(2, new java.sql.Date(courses.getCourseYear().getTime()));
            ps.setInt(3, courses.getCourseName());
            ps.setString(4, courses.getCourseDescription());
            ps.setString(5, courses.getCourseRoom());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;*/
        return isCourseChanged(courses,"UPDATE Courses SET CourseYear=?, CourseName=?, CourseDescription=? , CourseRoom=? WHERE CourseID=?");
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
    public Set<Courses> getSetOfCourses(String sqlStatement) {
        Connection conn = ConnectionFactory.getConnection();
        Set<Courses> coursesSet = new HashSet<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            Courses courses;
            while (rs.next()) {
                courses = new Courses(rs.getInt("CourseID"),
                        rs.getDate("CourseYear"),
                        rs.getInt("CourseName"),
                        rs.getString("CourseRoom"),
                        rs.getString("CourseDescription"));
                coursesSet.add(courses);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coursesSet;
    }
    public boolean isCourseChanged(Courses courses, String sqlStatement){
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ps.setDate(2, new java.sql.Date(courses.getCourseYear().getTime()));
            ps.setInt(3, courses.getCourseName());
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
}

















