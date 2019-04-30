package tests;

import model.course.Course;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.location.Location;
import model.skala.Skala;
import model.trainee.Trainee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrolledTraineesDaoImplTest {

    EnrolledTraineesDao enrolledTraineesDao;
    private Trainee trainee0;
    private Course course0;

    @Before
    public void preparedTest() {
        enrolledTraineesDao = new EnrolledTraineesDaoImpl();
        trainee0 = new Trainee();
        trainee0.setTraineeID(1);
        trainee0.setLocation(new Location(1, "City"));
        trainee0.setEmail("firstName1.lLastName1@mail.com");
        trainee0.setAddress("address1");
        trainee0.setSchool("Info");
        trainee0.setFirstName("FirstName1");
        trainee0.setLastName("LastName1");
        trainee0.setBirthday(LocalDate.of(1990, 01, 01));
        course0 = new Course(1, LocalDate.of(2018, 04, 01), LocalDate.of(2018, 10, 01), "Java Trainee", "EG01", "");

    }

    @Test
    public void getAllCoursesByTrainee() {

        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course(1, LocalDate.of(2018, 04, 01), LocalDate.of(2018, 10, 01), "Java Trainee", "EG01", "");
        Course course2 = new Course(2, LocalDate.of(2018, 10, 01), LocalDate.of(2019, 04, 01), "Java Trainee", "EG02", "");
        List<Course> coursesByTrainee = enrolledTraineesDao.getAllCoursesByTrainee(trainee0);
        courseList.add(course1);
        courseList.add(course2);
        Assert.assertEquals(courseList.size(), coursesByTrainee.size());
        for (int i = 0; i < courseList.size(); i++) {
            Assert.assertEquals(courseList.get(i).getCourseID(), coursesByTrainee.get(i).getCourseID());
            Assert.assertEquals(courseList.get(i).getCourseName(), coursesByTrainee.get(i).getCourseName());
            Assert.assertEquals(courseList.get(i).getCourseDescription(), coursesByTrainee.get(i).getCourseDescription());
            Assert.assertEquals(courseList.get(i).getCourseRoom(), coursesByTrainee.get(i).getCourseRoom());
            Assert.assertEquals(courseList.get(i).getStartDate(), coursesByTrainee.get(i).getStartDate());
            Assert.assertEquals(courseList.get(i).getEndDate(), coursesByTrainee.get(i).getEndDate());
        }

    }

    @Test
    public void getAllSkalaByTrainee() {
        List<Skala> skalaList = new ArrayList<>();
        Skala skala1 = new Skala(1, "Beginner");
        skalaList.add(skala1);
        skalaList.add(skala1);
        List<Skala> allSkalaByTrainee = enrolledTraineesDao.getAllSkalaByTrainee(trainee0);
        Assert.assertEquals(skalaList.size(), allSkalaByTrainee.size());
        for (int i = 0; i < skalaList.size(); i++) {
            Assert.assertEquals(skalaList.get(i).getSkalaId(), allSkalaByTrainee.get(i).getSkalaId());
            Assert.assertEquals(skalaList.get(i).getSkalaName(), allSkalaByTrainee.get(i).getSkalaName());
        }


    }

    @Test
    public void getAllTraineesByCourse() {
        List<Trainee> allTraineesByCourse = enrolledTraineesDao.getAllTraineesByCourse(course0);
        Trainee trainee1 = new Trainee(2, "LastName2", "FirstName2", LocalDate.of(1991, 02, 02), "address2", "Info", "firstName2.lLastName2@mail.com", new Location(2, "Stadt"));
        Trainee trainee2 = new Trainee(3, "LastName3", "FirstName3", LocalDate.of(1992, 03, 10), "address3", "Info", "firstName3.lLastName3@mail.com", new Location(3, "Ville"));
        List<Trainee> traineeList = new ArrayList<>();
        traineeList.add(trainee0);
        traineeList.add(trainee1);
        traineeList.add(trainee2);
        Assert.assertEquals(traineeList.size(), allTraineesByCourse.size());
        for (int i = 0; i < traineeList.size(); i++) {
            Assert.assertEquals(traineeList.get(i).getTraineeID(), allTraineesByCourse.get(i).getTraineeID());
            Assert.assertEquals(traineeList.get(i).getAddress(), allTraineesByCourse.get(i).getAddress());
            Assert.assertEquals(traineeList.get(i).getBirthday(), allTraineesByCourse.get(i).getBirthday());
            Assert.assertEquals(traineeList.get(i).getEmail(), allTraineesByCourse.get(i).getEmail());
            Assert.assertEquals(traineeList.get(i).getFirstName(), allTraineesByCourse.get(i).getFirstName());
            Assert.assertEquals(traineeList.get(i).getLastName(), allTraineesByCourse.get(i).getLastName());
            Assert.assertEquals(traineeList.get(i).getSchool(), allTraineesByCourse.get(i).getSchool());
            Assert.assertEquals(traineeList.get(i).getLocation().getLocationId(), allTraineesByCourse.get(i).getLocation().getLocationId());
            Assert.assertEquals(traineeList.get(i).getLocation().getLocationName(), allTraineesByCourse.get(i).getLocation().getLocationName());
        }

    }

    @Test
    public void getAllSkalaByCourse() {
    }

    @Test
    public void getAllTraineesBySkala() {
    }

    @Test
    public void getAllCoursesBySkala() {
    }

    @Test
    public void getSkala() {
    }

    @Test
    public void getCourse() {
    }

    @Test
    public void getTrainee() {
    }

    @Test
    public void getEnrolledTraineesByCourseID() {
    }

    @Test
    public void getEnrolledTraineesByTraineeID() {
    }

    @Test
    public void insertEnrolledTrainees() {
    }

    @Test
    public void deleteEnrolledTrainees() {
    }

    @Test
    public void deleteEnrolledTraineesByTrainee() {
    }

    @Test
    public void deleteEnrolledTraineesByCourse() {
    }
}