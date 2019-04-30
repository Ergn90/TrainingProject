package tests;

import model.course.Course;
import model.enrolledTrainees.EnrolledTrainees;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.location.Location;
import model.skala.Skala;
import model.trainee.Trainee;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnrolledTraineesDaoImplTest {

    EnrolledTraineesDao enrolledTraineesDao;
    private Trainee trainee0;
    private Course course0;
    private Skala skala0;
    private Course course1;
    private Trainee trainee1;
    private Trainee trainee2;
    private Skala skala3;

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
        course1 = new Course(2, LocalDate.of(2018, 10, 01), LocalDate.of(2019, 04, 01), "Java Trainee", "EG02", "");
        skala0 = new Skala(1, "Beginner");
        trainee1 = new Trainee(2, "LastName2", "FirstName2", LocalDate.of(1991, 02, 02), "address2", "Info", "firstName2.lLastName2@mail.com", new Location(2, "Stadt"));
        trainee2 = new Trainee(3, "LastName3", "FirstName3", LocalDate.of(1992, 03, 10), "address3", "Info", "firstName3.lLastName3@mail.com", new Location(3, "Ville"));
        skala3 = new Skala(3, "Advanced");

    }

    @Test
    public void getAllCoursesByTrainee() {

        List<Course> courseList = new ArrayList<>();
        List<Course> coursesByTrainee = enrolledTraineesDao.getAllCoursesByTrainee(trainee0);
        courseList.add(course0);
        courseList.add(course1);
        checkCourseList(coursesByTrainee, courseList);

    }

    @Test
    public void getAllSkalaByTrainee() {
        List<Skala> skalaList = new ArrayList<>();
        skalaList.add(skala0);
        skalaList.add(skala0);
        List<Skala> allSkalaByTrainee = enrolledTraineesDao.getAllSkalaByTrainee(trainee0);
        checkSkalaList(skalaList, allSkalaByTrainee);


    }

    private void checkSkalaList(List<Skala> skalaList, List<Skala> allSkalaByTrainee) {
        Assert.assertEquals(skalaList.size(), allSkalaByTrainee.size());
        for (int i = 0; i < skalaList.size(); i++) {
            Skala excepted = skalaList.get(i);
            Skala attend = allSkalaByTrainee.get(i);
            checkSkala(excepted, attend);
        }
    }

    private void checkSkala(Skala excepted, Skala attend) {
        Assert.assertEquals(excepted.getSkalaId(), attend.getSkalaId());
        Assert.assertEquals(excepted.getSkalaName(), attend.getSkalaName());
    }

    @Test
    public void getAllTraineesByCourse() {
        List<Trainee> allTraineesByCourse = enrolledTraineesDao.getAllTraineesByCourse(course0);
        List<Trainee> traineeList = new ArrayList<>();
        traineeList.add(trainee0);
        traineeList.add(trainee1);
        traineeList.add(trainee2);
        checkTraineeList(allTraineesByCourse, traineeList);

    }

    @Test
    public void getAllSkalaByCourse() {
        List<Skala> allSkalaByCourse = enrolledTraineesDao.getAllSkalaByCourse(course0);
        List<Skala> exceptedList = new ArrayList<>();
        exceptedList.add(skala0);
        exceptedList.add(skala3);
        exceptedList.add(skala3);
        checkSkalaList(exceptedList, allSkalaByCourse);
    }

    @Test
    public void getAllTraineesBySkala() {
        List<Trainee> allTraineesBySkala = enrolledTraineesDao.getAllTraineesBySkala(skala0);
        List<Trainee> traineeList = new ArrayList<>();
        traineeList.add(trainee0);
        traineeList.add(trainee0);
        checkTraineeList(allTraineesBySkala, traineeList);


    }

    private void checkTraineeList(List<Trainee> allTraineesBySkala, List<Trainee> traineeList) {
        Assert.assertEquals(traineeList.size(), allTraineesBySkala.size());
        for (int i = 0; i < traineeList.size(); i++) {
            Trainee excepted = traineeList.get(i);
            Trainee attend = allTraineesBySkala.get(i);
            checkTrainee(excepted, attend);
        }
    }

    private void checkTrainee(Trainee excepted, Trainee attend) {
        Assert.assertEquals(excepted.getTraineeID(), attend.getTraineeID());
        Assert.assertEquals(excepted.getAddress(), attend.getAddress());
        Assert.assertEquals(excepted.getBirthday(), attend.getBirthday());
        Assert.assertEquals(excepted.getEmail(), attend.getEmail());
        Assert.assertEquals(excepted.getFirstName(), attend.getFirstName());
        Assert.assertEquals(excepted.getLastName(), attend.getLastName());
        Assert.assertEquals(excepted.getSchool(), attend.getSchool());
        Assert.assertEquals(excepted.getLocation().getLocationId(), attend.getLocation().getLocationId());
        Assert.assertEquals(excepted.getLocation().getLocationName(), attend.getLocation().getLocationName());
    }

    @Test
    public void getAllCoursesBySkala() {
        List<Course> allCoursesBySkala = enrolledTraineesDao.getAllCoursesBySkala(skala0);
        List<Course> exceptedCourseList = new ArrayList<>();
        exceptedCourseList.add(course0);
        exceptedCourseList.add(course1);
        checkCourseList(allCoursesBySkala, exceptedCourseList);


    }

    private void checkCourseList(List<Course> allCoursesBySkala, List<Course> exceptedCourseList) {
        Assert.assertEquals(exceptedCourseList.size(), allCoursesBySkala.size());
        for (int i = 0; i < exceptedCourseList.size(); i++) {
            Course excepted = exceptedCourseList.get(i);
            Course attend = allCoursesBySkala.get(i);
            checkCourse(excepted, attend);
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
    public void getSkala() {
        List<Skala> attend = enrolledTraineesDao.getSkala(1, 1);
        checkSkalaList(Collections.singletonList(skala0), attend);
    }

    @Test
    public void getCourse() {
        List<Course> attend = enrolledTraineesDao.getCourse(1, 1);
        List<Course> excepted = new ArrayList<>();
        excepted.add(course0);
        excepted.add(course1);
        checkCourseList(excepted, attend);
    }

    @Test
    public void getTrainee() {
        List<Trainee> attend = enrolledTraineesDao.getTrainee(1, 1);
        checkTraineeList(Collections.singletonList(trainee0), attend);
    }

    @Test
    public void getEnrolledTraineesByCourseID() {
        List<EnrolledTrainees> enrolledTraineesByCourseID = enrolledTraineesDao.getEnrolledTraineesByCourseID(1);
        List<EnrolledTrainees> exceptedList = new ArrayList<>();
        EnrolledTrainees enrolledTrainees0 = new EnrolledTrainees(course0, trainee0, skala0);
        EnrolledTrainees enrolledTrainees1 = new EnrolledTrainees(course0, trainee1, skala3);
        EnrolledTrainees enrolledTrainees2 = new EnrolledTrainees(course0, trainee2, skala3);
        exceptedList.add(enrolledTrainees0);
        exceptedList.add(enrolledTrainees1);
        exceptedList.add(enrolledTrainees2);
        checkEnrolledList(enrolledTraineesByCourseID, exceptedList);

    }

    private void checkEnrolledList(List<EnrolledTrainees> enrolledTraineesByCourseID, List<EnrolledTrainees> exceptedList) {
        Assert.assertEquals(exceptedList.size(), enrolledTraineesByCourseID.size());
        for (int i = 0; i < exceptedList.size(); i++) {
            EnrolledTrainees attend = enrolledTraineesByCourseID.get(i);
            EnrolledTrainees excepted = exceptedList.get(i);
            checkEnrolledTrainee(attend, excepted);
        }
    }

    private void checkEnrolledTrainee(EnrolledTrainees attend, EnrolledTrainees excepted) {
        checkTrainee(excepted.getTrainee(), attend.getTrainee());
        checkCourse(excepted.getCourse(), attend.getCourse());
        checkSkala(excepted.getSkala(), attend.getSkala());
    }

    @Test
    public void getEnrolledTraineesByTraineeID() {
        List<EnrolledTrainees> enrolledTraineesByCourseID = enrolledTraineesDao.getEnrolledTraineesByTraineeID(1);
        List<EnrolledTrainees> exceptedList = new ArrayList<>();
        EnrolledTrainees enrolledTrainees0 = new EnrolledTrainees(course0, trainee0, skala0);
        EnrolledTrainees enrolledTrainees1 = new EnrolledTrainees(course1, trainee0, skala0);
        exceptedList.add(enrolledTrainees0);
        exceptedList.add(enrolledTrainees1);
        checkEnrolledList(enrolledTraineesByCourseID, exceptedList);

    }

    @Test
    public void getEnrolledTrainees() {
        EnrolledTrainees attend = enrolledTraineesDao.getEnrolledTrainees(1, 1, 1);
        EnrolledTrainees excepted = new EnrolledTrainees(course0, trainee0, skala0);
        checkEnrolledTrainee(attend, excepted);

    }

    @Test
    public void insertEnrolledTrainees() {
        boolean isInserted = enrolledTraineesDao.insertEnrolledTrainees(course1, skala0, trainee1);
        Assert.assertTrue(isInserted);
        EnrolledTrainees excepted = new EnrolledTrainees(course1, trainee1, skala0);
        EnrolledTrainees attend = enrolledTraineesDao.getEnrolledTrainees(2, 1, 2);
        checkEnrolledTrainee(attend, excepted);
        resetInsert();
    }


    public  void resetInsert() {
        EnrolledTraineesDao enrolledTraineesDao = new EnrolledTraineesDaoImpl();

        Course course1 = new Course(2, LocalDate.of(2018, 10, 01), LocalDate.of(2019, 04, 01), "Java Trainee", "EG02", "");
        Skala skala0 = new Skala(1, "Beginner");
        Trainee trainee1 = new Trainee(2, "LastName2", "FirstName2", LocalDate.of(1991, 02, 02), "address2", "Info", "firstName2.lLastName2@mail.com", new Location(2, "Stadt"));

        enrolledTraineesDao.deleteEnrolledTrainees(course1.getCourseID(), skala0.getSkalaId(), trainee1.getTraineeID());

    }


}