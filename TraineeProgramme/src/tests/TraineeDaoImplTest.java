package tests;

import model.course.Course;
import model.enrolledTrainees.EnrolledTrainees;
import model.enrolledTrainees.EnrolledTraineesDao;
import model.enrolledTrainees.EnrolledTraineesDaoImpl;
import model.location.Location;
import model.skala.Skala;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TraineeDaoImplTest {


    TraineeDao traineeDao;
    private Trainee trainee0;
    private Trainee trainee1;
    private Trainee trainee2;

    @Before
    public void preparedTest() {
        traineeDao = new TraineeDaoImpl();
        trainee0 = new Trainee();
        trainee0.setTraineeID(1);
        trainee0.setLocation(new Location(1, "City"));
        trainee0.setEmail("firstName1.lLastName1@mail.com");
        trainee0.setAddress("address1");
        trainee0.setSchool("Info");
        trainee0.setFirstName("FirstName1");
        trainee0.setLastName("LastName1");
        trainee0.setBirthday(LocalDate.of(1990, 01, 01));
        trainee1 = new Trainee(2, "LastName2", "FirstName2", LocalDate.of(1991, 02, 02), "address2", "Info", "firstName2.lLastName2@mail.com", new Location(2, "Stadt"));
        trainee2 = new Trainee(3, "LastName3", "FirstName3", LocalDate.of(1992, 03, 10), "address3", "Info", "firstName3.lLastName3@mail.com", new Location(3, "Ville"));

    }
    @Test
    public void getTrainee() {
        List<Trainee> attend = (List<Trainee>) traineeDao.getTrainee(2);
        checkTraineeList(Collections.singletonList(trainee2), attend);
    }

    private void checkTraineeList(List<Trainee> allTraineesByID, List<Trainee> traineeList) {
        Assert.assertEquals(traineeList.size(), allTraineesByID.size());
        for (int i = 0; i < traineeList.size(); i++) {
            Trainee excepted = traineeList.get(i);
            Trainee attend = allTraineesByID.get(i);
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
    public void getAllTrainee() {
        List<Trainee> traineeList = new ArrayList<>();
        traineeList.add(trainee0);
        traineeList.add(trainee1);
        List<Trainee> allTrainee = (List<Trainee>) traineeDao.getTrainee(trainee1.getTraineeID());
        checkTraineeList(traineeList, allTrainee);
    }


    @Test
    public void insertTrainee() {
        boolean isInserted = traineeDao.insertTrainee(trainee1);
        Assert.assertTrue(isInserted);
        Trainee excepted = new Trainee(trainee1.getTraineeID(),trainee1.getLastName(),trainee1.getFirstName(),
                 trainee1.getBirthday(),trainee1.getAddress(),trainee1.getSchool(),trainee1.getEmail(),trainee1.getLocation());
        Trainee attend = traineeDao.getTrainee(1);
        checkTrainee(attend, excepted);
        resetInsert();
    }


    public  void resetInsert() {
        TraineeDao traineeDao = new TraineeDaoImpl();
        Trainee trainee1 = new Trainee(2, "LastName2", "FirstName2", LocalDate.of(1991, 02, 02), "address2", "Info", "firstName2.lLastName2@mail.com", new Location(2, "Stadt"));
        traineeDao.deleteTrainee(trainee1.getTraineeID());
    }

}