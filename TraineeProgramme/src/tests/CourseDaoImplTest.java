package tests;

import model.course.Course;
import model.course.CourseDao;
import model.course.CourseDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CourseDaoImplTest {

    private CourseDao courseDao;
    private Course course0;
    private Course course1;
    private Course course6;
    private Course course10;

    @Before
    public void preparedTest() {
        courseDao = new CourseDaoImpl();
        course0 = new Course(1, LocalDate.of(2018, 04, 01), LocalDate.of(2018, 10, 01), "Java Trainee", "EG01", "");
        course1 = new Course(2, LocalDate.of(2018, 10, 01), LocalDate.of(2019, 04, 01), "Java Trainee", "EG02", "");
        course6 = new Course(6, LocalDate.of(2020, 04, 01), LocalDate.of(2020, 10, 01), "Java Trainee", "EG01", "new Insert");
    }


    private void checkCourseList(List<Course> attendList, List<Course> exceptedCourseList) {
        Assert.assertEquals(exceptedCourseList.size(), attendList.size());
        for (int i = 0; i < exceptedCourseList.size(); i++) {
            Course excepted = exceptedCourseList.get(i);
            Course attend = attendList.get(i);
            checkCourse(excepted, attend);
        }
    }

    private void checkTraineeList(List<Course> allTraineesByID, Set<Course> traineeList) {
        Assert.assertEquals(traineeList.size(), allTraineesByID.size());


        for (Iterator<Course> it = traineeList.iterator(); it.hasNext(); ) {
            Course f = it.next();
            checkCourse(allTraineesByID.get(f.getCourseID() - 1), f);

        }

    }

    private void checkCourse(Course excepted, Course attend) {
        Assert.assertEquals(excepted.getCourseID(), attend.getCourseID());
        Assert.assertEquals(excepted.getCourseName(), attend.getCourseName());
        Assert.assertEquals(excepted.getCourseDescription(), attend.getCourseDescription());
        Assert.assertEquals(excepted.getCourseRoom(), attend.getCourseRoom());
        Assert.assertEquals(excepted.getStartDate(), attend.getStartDate());
        Assert.assertEquals(excepted.getEndDate(), attend.getEndDate());
    }

    @Test
    public void getCourse() {
        Course course = courseDao.getCourse(1);
        checkCourse(course0, course);
    }

    @Test
    public void getAllCourses() {
        Course course2 = new Course(3, LocalDate.of(2019, 04, 01), LocalDate.of(2019, 10, 01), "Java Trainee", "EG01", "current course");
        Course course3 = new Course(4, LocalDate.of(2019, 10, 01), LocalDate.of(2020, 04, 01), "Java Trainee", "EG02", "next course");
        Course course4 = new Course(5, LocalDate.of(2020, 04, 01), LocalDate.of(2020, 10, 01), "Java Trainee", "EG01", "next course");

        Set<Course> allCourses = courseDao.getAllCourses();
        List<Course> courseList = new ArrayList<>();
        courseList.add(course0);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        checkTraineeList(courseList, allCourses);

    }

    @Test
    public void getCoursesByName() {
        Set<Course> java_trainee = courseDao.getCoursesByName("Java Trainee");
        Course course2 = new Course(3, LocalDate.of(2019, 04, 01), LocalDate.of(2019, 10, 01), "Java Trainee", "EG01", "current course");
        Course course3 = new Course(4, LocalDate.of(2019, 10, 01), LocalDate.of(2020, 04, 01), "Java Trainee", "EG02", "next course");
        Course course4 = new Course(5, LocalDate.of(2020, 04, 01), LocalDate.of(2020, 10, 01), "Java Trainee", "EG01", "next course");

        List<Course> courseList = new ArrayList<>();
        courseList.add(course0);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        checkTraineeList(courseList, java_trainee);
    }

    @Test
    public void insertCourse() {
        boolean isInserted = courseDao.insertCourse(course6);
        Assert.assertTrue(isInserted);
        Course excepted = new Course(course6.getCourseID(), course6.getStartDate(), course6.getEndDate(),
                course6.getCourseName(), course6.getCourseRoom(), course6.getCourseDescription());
        Course attend = courseDao.getCourse(6);
        checkCourse(attend, excepted);
        courseDao.deleteCourse(course6.getCourseID());
    }


    @Test
    public void updateCourse() {
        Course excepted = new Course(course1.getCourseID(), course1.getStartDate(), course1.getEndDate(),
                course1.getCourseName(), course1.getCourseRoom(), course1.getCourseDescription());
        excepted.setCourseName("test");
        boolean isUpdaded = courseDao.updateCourse(excepted);
        Assert.assertTrue(isUpdaded);
        Course attend = new Course(course1.getCourseID(), course1.getStartDate(), course1.getEndDate(),
                "test", course1.getCourseRoom(), course1.getCourseDescription());
        checkCourse(attend, excepted);
    }


}