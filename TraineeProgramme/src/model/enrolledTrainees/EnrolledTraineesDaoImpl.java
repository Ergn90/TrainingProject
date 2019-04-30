package model.enrolledTrainees;

import model.ConnectionFactory;
import model.course.Course;
import model.location.Location;
import model.skala.Skala;
import model.trainee.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrolledTraineesDaoImpl implements EnrolledTraineesDao {
    private static final String SELECTCOURSESBYTRAINEESID = "SELECT courses.CourseID,CourseName,CourseStartDate,CourseEndDate,CourseRoom,CourseDescription from courses " +
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
    private static final String SELECTCOURSESBYSKALAID = "SELECT courses.CourseID,CourseName,CourseStartDate,CourseEndDate,CourseRoom,CourseDescription from courses " +
            "inner join enrolled_trainees on  enrolled_trainees.CourseID=courses.CourseID " +
            "where enrolled_trainees.SkalaID=";
    private static final String SELECTALLINFO = "SELECT trainee.TraineeID,LastName,FirstName,Birthday,Address,School,Email,trainee.LocationId,LocationName," +
            "skala.SkalaId,SkalaName," +
            "courses.CourseID,CourseName,CourseStartDate,CourseEndDate,CourseRoom,CourseDescription FROM trainee_programm_db.enrolled_trainees " +
            "inner join trainee on trainee.TraineeID=enrolled_trainees.TraineeID " +
            "inner join location on location.LocationId=trainee.TraineeID " +
            "inner join skala on skala.SkalaId=enrolled_trainees.SkalaID " +
            "inner join courses on courses.CourseID=enrolled_trainees.CourseID";
    private static final String WHERECOURSEID = " where enrolled_trainees.CourseID=";
    private static final String WHERETRAINEEID = " where enrolled_trainees.TraineeID=";
    private static final String ANDTRAINEEID = " and enrolled_trainees.TraineeID=";
    private static final String ANDCOURSEID = " and enrolled_trainees.CourseID=";
    private static final String ANDSKALAEID = " and enrolled_trainees.SkalaID=";

    private Skala extractSkalaFromResult(ResultSet resultSet) throws SQLException {
        Skala skala = new Skala();
        skala.setSkalaId(resultSet.getInt("SkalaId"));
        skala.setSkalaName(resultSet.getString("SkalaName"));

        return skala;
    }

    private Course extractCourseFromResult(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseID(resultSet.getInt("CourseID"));
        course.setCourseName(resultSet.getString("CourseName"));
        course.setStartDate(resultSet.getDate("CourseStartDate").toLocalDate());
        course.setEndDate(resultSet.getDate("CourseEndDate").toLocalDate());
        course.setCourseRoom(resultSet.getString("CourseRoom"));
        course.setCourseDescription(resultSet.getString("CourseDescription"));
        return course;

    }

    private Trainee extractTraineeFromResult(ResultSet resultSet) throws SQLException {
        Trainee trainee = new Trainee();
        trainee.setTraineeID(resultSet.getInt("TraineeID"));
        trainee.setLastName(resultSet.getString("LastName"));
        trainee.setFirstName(resultSet.getString("FirstName"));
        trainee.setBirthday(resultSet.getDate("Birthday").toLocalDate());
        trainee.setAddress(resultSet.getString("Address"));
        trainee.setSchool(resultSet.getString("School"));
        trainee.setEmail(resultSet.getString("Email"));
        Location location = new Location();
        location.setLocationId(resultSet.getInt("LocationID"));
        location.setLocationName(resultSet.getString("LocationName"));
        trainee.setLocation(location);
        return trainee;
    }

    @Override
    public List<Course> getAllCoursesByTrainee(Trainee trainee) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTCOURSESBYTRAINEESID + trainee.getTraineeID());
            List<Course> coursesList = new ArrayList<>();
            while (resultSet.next()) {
                Course course = extractCourseFromResult(resultSet);
                coursesList.add(course);
            }
            return coursesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Skala> getAllSkalaByTrainee(Trainee trainee) {

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
    public List<Trainee> getAllTraineesByCourse(Course course) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTTRAINEEBYCOURSEID + course.getCourseID());
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
    public List<Skala> getAllSkalaByCourse(Course course) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTSKALABYCOURSEID + course.getCourseID());
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
    public List<Course> getAllCoursesBySkala(Skala skala) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTCOURSESBYSKALAID + skala.getSkalaId());
            List<Course> coursesList = new ArrayList<>();
            while (resultSet.next()) {
                Course course = extractCourseFromResult(resultSet);
                coursesList.add(course);
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
    public Course getCourse(int traineeID, int skalaID) {
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
    public List<EnrolledTrainees> getEnrolledTraineesByCourseID(int courseID) {
        return getEnrolledTrainees(WHERECOURSEID, courseID);
    }

    private List<EnrolledTrainees> getEnrolledTrainees(String where, int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTALLINFO + where + id);
            List<EnrolledTrainees> enrolledTraineesList = new ArrayList<>();
            while (resultSet.next()) {
                extractEnrolledTrainees(resultSet, enrolledTraineesList);
            }
            return enrolledTraineesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EnrolledTrainees> getEnrolledTraineesByTraineeID(int traineeID) {
        return getEnrolledTrainees(WHERETRAINEEID, traineeID);
    }

    private void extractEnrolledTrainees(ResultSet resultSet, List<EnrolledTrainees> enrolledTraineesList) throws SQLException {
        Course course = extractCourseFromResult(resultSet);
        Trainee trainee = extractTraineeFromResult(resultSet);
        Skala skala = extractSkalaFromResult(resultSet);
        EnrolledTrainees enrolledTrainees = new EnrolledTrainees(course, trainee, skala);
        enrolledTraineesList.add(enrolledTrainees);
    }

    @Override
    public boolean insertEnrolledTrainees(Course course, Skala skala, Trainee trainee) {


        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO enrolled_trainees VALUES (?, ?, ?)");

            ps.setInt(1, trainee.getTraineeID());

            ps.setInt(2, course.getCourseID());

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
    public boolean deleteEnrolledTrainees(int traineeID, int skalaID, int coursesID) {

        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM enrolled_trainees WHERE traineeID=? and  CourseID=? and  SkalaId=?");

            ps.setInt(1, traineeID);
            ps.setInt(2, coursesID);
            ps.setInt(3, skalaID);

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
    public boolean deleteEnrolledTraineesByTrainee(int traineeID) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM enrolled_trainees WHERE traineeID=?");

            ps.setInt(1, traineeID);

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
    public boolean deleteEnrolledTraineesByCourse(int coursesID) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM enrolled_trainees WHERE CourseID=?");

            ps.setInt(1, coursesID);

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

