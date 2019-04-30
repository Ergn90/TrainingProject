package tests;

import model.course.Course;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.location.Location;
import model.trainee.Trainee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EnrolledTraineesDaoImplTest {

    EnrolledTraineesDao enrolledTraineesDao;

    @Before
    public void preparedTest() {
        enrolledTraineesDao = new EnrolledTraineesDaoImpl();
    }

    @Test
    public void getAllCoursesByTrainee() {
        Trainee trainee = new Trainee();
        trainee.setTraineeID(1);
        trainee.setLocation(new Location(1, "City"));
        trainee.setEmail("firstName1.lLastName1@mail.com");
        trainee.setAddress("address1");
        trainee.setSchool("Info");
        trainee.setBirthday(LocalDate.of(1990, 01, 01));
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course(1, LocalDate.of(2018, 04, 01), LocalDate.of(2018, 10, 01), "Java Trainee", "EG01", "");
        Course course2 = new Course(2, LocalDate.of(2018, 10, 01), LocalDate.of(2019, 04, 01), "Java Trainee", "EG02", "");
        List<Course> coursesByTrainee = enrolledTraineesDao.getAllCoursesByTrainee(trainee);
        courseList.add(course1);
        courseList.add(course2);
        Assert.assertEquals(courseList.size(), coursesByTrainee.size());
        Assert.assertEquals(courseList.get(0).getCourseID(), coursesByTrainee.get(0).getCourseID());
        Assert.assertEquals(courseList.get(0).getCourseName(), coursesByTrainee.get(0).getCourseName());
        Assert.assertEquals(courseList.get(0).getCourseDescription(), coursesByTrainee.get(0).getCourseDescription());
        Assert.assertEquals(courseList.get(0).getCourseRoom(), coursesByTrainee.get(0).getCourseRoom());
        Assert.assertEquals(courseList.get(0).getStartDate(), coursesByTrainee.get(0).getStartDate());
        Assert.assertEquals(courseList.get(0).getEndDate(), coursesByTrainee.get(0).getEndDate());
        Assert.assertEquals(courseList.get(1).getCourseID(), coursesByTrainee.get(1).getCourseID());
        Assert.assertEquals(courseList.get(1).getCourseName(), coursesByTrainee.get(1).getCourseName());
        Assert.assertEquals(courseList.get(1).getCourseDescription(), coursesByTrainee.get(1).getCourseDescription());
        Assert.assertEquals(courseList.get(1).getCourseRoom(), coursesByTrainee.get(1).getCourseRoom());
        Assert.assertEquals(courseList.get(1).getStartDate(), coursesByTrainee.get(1).getStartDate());
        Assert.assertEquals(courseList.get(1).getEndDate(), coursesByTrainee.get(1).getEndDate());

    }

    @Test
    public void getAllSkalaByTrainee() {
    }

    @Test
    public void getAllTraineesByCourse() {
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