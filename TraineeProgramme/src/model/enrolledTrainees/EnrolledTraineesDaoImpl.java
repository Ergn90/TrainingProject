package model.enrolledTrainees;

import model.ConnectionFactory;
import model.courses.Courses;
import model.location.Location;
import model.skala.Skala;
import model.trainee.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrolledTraineesDaoImpl implements EnrolledTraineesDao {
    private static final String SELECTCOURSESBYTRAINEESID = "SELECT courses.CourseID,CourseName,CourseDate,CourseRoom,CourseDescription from courses " +
            "inner join enrolled_trainees on  enrolled_trainees.CourseID=courses.CourseID " +
            "where enrolled_trainees.TraineeID=";
    private static final String SELECTSKALABYTRAINEESID = "SELECT skala.SkalaId,SkalaName " +
            "from skala inner join enrolled_trainees on  enrolled_trainees.SkalaID=skala.SkalaId " +
            "where enrolled_trainees.TraineeID=";

    private static final String SELECTSKALABYCOURSEID = "SELECT skala.SkalaId,SkalaName " +
            "from skala inner join enrolled_trainees on  enrolled_trainees.SkalaID=skala.SkalaId " +
            "where enrolled_trainees.CourseID=";
    private static final String SELECTTRAINEEBYCOURSEID = "SELECT trainee.TraineeID,LastName,    FirstName,    Birthday,    Address,    School,Email,    trainee.LocationId,LocationName " +
            "from trainee inner join enrolled_trainees on enrolled_trainees.TraineeID=trainee.TraineeID " +
            "inner join location on trainee.LocationId=location.LocationId " +
            "where enrolled_trainees.CourseID=";
    private static final String SELECTTRAINEEBYSKALAID = "SELECT trainee.TraineeID,LastName,    FirstName,    Birthday,    Address,    School,Email,    trainee.LocationId,LocationName " +
            "from trainee inner join enrolled_trainees on enrolled_trainees.TraineeID=trainee.TraineeID " +
            "inner join location on trainee.LocationId=location.LocationId " +
            "where  enrolled_trainees.SkalaID=";
    private static final String SELECTCOURSESBYSKALAID = "SELECT courses.CourseID,CourseName,CourseDate,CourseRoom,CourseDescription from courses " +
            "inner join enrolled_trainees on  enrolled_trainees.CourseID=courses.CourseID " +
            "where enrolled_trainees.SkalaID=";
    private static final String ANDTRAINEEID = " and enrolled_trainees.TraineeID=";
    private static final String ANDCOURSEID = " and enrolled_trainees.CourseID=";
    private static final String ANDSKALAEID = " and enrolled_trainees.SkalaID=";

    private Skala extractSkalaFromResult(ResultSet resultSet) throws SQLException {
        Skala skala = new Skala();
        skala.setSkalaId(resultSet.getInt("SkalaId"));
        skala.setSkalaName(resultSet.getString("SkalaName"));

        return skala;
    }

    private Courses extractCourseFromResult(ResultSet resultSet) throws SQLException {
        Courses courses = new Courses();
        courses.setCourseID(resultSet.getInt("CourseID"));
        courses.setCourseName(resultSet.getString("CourseName"));
        courses.setStartDate(resultSet.getDate("CourseDate"));
        courses.setCourseDescription(resultSet.getString("CourseDescription"));
        return courses;

    }

    private Trainee extractTraineeFromResult(ResultSet resultSet) throws SQLException {
        Trainee trainee = new Trainee();
        trainee.setTraineeID(resultSet.getInt("TraineeID"));
        trainee.setAddress(resultSet.getString("LastName"));
        trainee.setFirstName(resultSet.getString("FirstName"));
        trainee.setBirthday(resultSet.getDate("Birthday"));
        trainee.setAddress(resultSet.getString("Address"));
        trainee.setEmail(resultSet.getString("Email"));
        Location location = new Location();
        location.setLocationId(resultSet.getInt("LocationID"));
        location.setLocationName(resultSet.getString("LocationName"));
        trainee.setLocation(location);
        return trainee;
    }

    @Override
    public List<Courses> getAllCoursesByTrainees(Trainee trainee) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTCOURSESBYTRAINEESID + trainee.getTraineeID());
            List<Courses> coursesList = new ArrayList<>();
            while (resultSet.next()) {
                Courses courses = extractCourseFromResult(resultSet);
                coursesList.add(courses);
            }
            return coursesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Skala> getAllSkalaByTraines(Trainee trainee) {

        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTSKALABYTRAINEESID + trainee.getTraineeID());
            List<Skala> skalaList = new ArrayList<>();
            while (resultSet.next()) {
                Skala skala = extractSkalaFromResult(resultSet);
                skalaList.add(skala);
            }
            return skalaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Trainee> getAllTraineesByCourses(Courses courses) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTTRAINEEBYCOURSEID + courses.getCourseID());
            List<Trainee> traineeList = new ArrayList<>();
            while (resultSet.next()) {
                Trainee trainee = extractTraineeFromResult(resultSet);
                traineeList.add(trainee);
            }
            return traineeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Skala> getAllSkalaByCourses(Courses courses) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTSKALABYCOURSEID + courses.getCourseID());
            List<Skala> skalaList = new ArrayList<>();
            while (resultSet.next()) {
                Skala skala = extractSkalaFromResult(resultSet);
                skalaList.add(skala);
            }
            return skalaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Trainee> getAllTraineesBySkala(Skala skala) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTTRAINEEBYSKALAID + skala.getSkalaId());
            List<Trainee> traineeList = new ArrayList<>();
            while (resultSet.next()) {
                Trainee trainee = extractTraineeFromResult(resultSet);
                traineeList.add(trainee);
            }
            return traineeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Courses> getAllCoursesBySkala(Skala skala) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTCOURSESBYSKALAID + skala.getSkalaId());
            List<Courses> coursesList = new ArrayList<>();
            while (resultSet.next()) {
                Courses courses = extractCourseFromResult(resultSet);
                coursesList.add(courses);
            }
            return coursesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Skala getSkala(int traineeID, int coursesID) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(SELECTSKALABYCOURSEID + coursesID + ANDTRAINEEID + traineeID);

            if (rs.next()) {
                return extractSkalaFromResult(rs);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;

    }

    @Override
    public Courses getCourse(int traineeID, int skalaID) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(SELECTCOURSESBYTRAINEESID + traineeID + ANDSKALAEID + skalaID);

            if (rs.next()) {
                return extractCourseFromResult(rs);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public Trainee getTrainee(int coursesID, int skalaID) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(SELECTTRAINEEBYCOURSEID + coursesID + ANDSKALAEID + skalaID);

            if (rs.next()) {
                return extractTraineeFromResult(rs);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public boolean insertEnrolledTraines(Courses courses, Skala skala, Trainee trainee) {


        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO enrolled_trainees VALUES (?, ?, ?)");

            ps.setInt(1, trainee.getTraineeID());

            ps.setInt(2, courses.getCourseID());

            ps.setInt(3, skala.getSkalaId());

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
    public boolean deletEnrolledTraines(int traineeID, int skalaID, int coursesID) {

        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM enrolled_trainees WHERE traineeID=?, CourseID=?, SkalaId=?");

            ps.setInt(1, traineeID);
            ps.setInt(2, coursesID);
            ps.setInt(3, skalaID);

            int i = ps.executeUpdate();
            if(i == 1) {

                return true;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;
    }
}

