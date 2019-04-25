package model.enrolledTrainees;

import model.ConnectionFactory;
import model.courses.Courses;
import model.skala.Skala;
import model.trainee.Trainee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnrolledTraineesDaoImpl implements EnrolledTraineesDao {
    private static final String SELECTALLCOURSESBYTRAINEESID = "SELECT courses.CourseID,CourseName,CourseYear,CourseRoom,CourseDescription " +
            "FROM courses,trainee,enrolled_trainees  " +
            "where   enrolled_trainees.TraineeID=";
    private static final String SELECTALLSKALABYTRAINEES = "SELECT skala.SkalaId,SkalaName " +
            "FROM courses,trainee,enrolled_trainees,skala  " +
            "where  enrolled_trainees.SkalaID=";

    @Override
    public List<Courses> getAllCoursesByTrainees(Trainee trainee) {
        Connection connection= ConnectionFactory.getConnection();

        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECTALLCOURSESBYTRAINEESID+trainee.getTraineeID());
            List<Courses> coursesList=new ArrayList<>();
            while(resultSet.next()){
                Courses  courses=extractCourseFromTrainees(resultSet);
                coursesList.add(courses);
            }
            return  coursesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Courses extractCourseFromTrainees(ResultSet resultSet) throws SQLException {
        Courses courses=new Courses();
        courses.setCourseID(resultSet.getInt("CourseID"));
        courses.setCourseName(resultSet.getInt("CourseName"));
        courses.setCourseYear(resultSet.getDate("CourseYear"));
        //courses.setCourseDescription(resultSet.getString("CourseDescription"));
        return  courses;

    }

    @Override
    public List<Skala> getAllSkalaByTraines(Trainee trainee) {

        Connection connection= ConnectionFactory.getConnection();

        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECTALLSKALABYTRAINEES+trainee.getTraineeID());
            List<Skala> skalaList=new ArrayList<>();
            while(resultSet.next()){
                Skala  skala=extractSkalaFromTraines(resultSet);
                skalaList.add(skala);
            }
            return  skalaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Skala extractSkalaFromTraines(ResultSet resultSet) throws SQLException {
        Skala skala=new Skala();
        skala.setSkalaId(resultSet.getInt("SkalaId"));
        skala.setSkalaName(resultSet.getString("SkalaName"));
       
        return  skala;
    }

    @Override
    public List<Trainee> getAllTraineesByCourses(Courses courses) {
        return null;
    }

    @Override
    public List<Skala> getAllSkalaByCourses(Courses courses) {
        return null;
    }

    @Override
    public List<Trainee> getAllTraineesBySkala(Skala skala) {
        return null;
    }

    @Override
    public List<Courses> getAllCoursesBySkala(Skala skala) {
        return null;
    }

    @Override
    public Skala getSkala(int traineeID, int coursesID) {
        return null;
    }

    @Override
    public Courses getCourse(int traineeID, int skalaID) {
        return null;
    }

    @Override
    public Trainee getTrainee(int coursesID, int skalaID) {
        return null;
    }

    @Override
    public boolean insertEnrolledTraines(Courses courses, Skala skala, Trainee trainee) {
        return false;
    }

    @Override
    public boolean updateEnrolledTraines(Courses courses, Skala skala, Trainee trainee) {
        return false;
    }

    @Override
    public boolean updateEnrolledTraines(int traineeID, int skalaID, int coursesID) {
        return false;
    }
}
