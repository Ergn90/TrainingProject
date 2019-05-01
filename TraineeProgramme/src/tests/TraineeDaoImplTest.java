package tests;

import model.location.Location;
import model.trainee.Trainee;
import model.trainee.TraineeDao;
import model.trainee.TraineeDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

public class TraineeDaoImplTest {


    TraineeDao traineeDao;
    private Trainee trainee0;
    private Trainee trainee1;
    private Trainee trainee10;

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
        trainee10 = new Trainee(10, "LastName3", "FirstName3", LocalDate.of(1992, 03, 10), "address3", "Info", "firstName3.lLastName3@mail.com", new Location(3, "Ville"));

    }
    @Test
    public void getTrainee() {
        Trainee attend =traineeDao.getTrainee(2);
        checkTrainee(trainee1, attend);
    }

    private void checkTraineeList(List<Trainee> allTraineesByID, Set<Trainee> traineeList) {
        Assert.assertEquals(traineeList.size(), allTraineesByID.size());


        for (Iterator<Trainee> it = traineeList.iterator(); it.hasNext(); ) {
            Trainee f = it.next();

                checkTrainee(allTraineesByID.get(f.getTraineeID()-1), f);

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
        traineeList.add(new Trainee(3, "LastName3", "FirstName3",LocalDate.of(1992,3,10),"address3","Info","firstName3.lLastName3@mail.com",new Location(3,"Ville")));
        traineeList.add(new Trainee(4, "LastName4", "FirstName4",LocalDate.of(1993,4,10),"address4","Info","firstName4.lLastName4@mail.com",new Location(4,"Ciudad")));
        traineeList.add(new Trainee(5, "LastName5", "FirstName5",LocalDate.of(1993,4,10),"address5","Info","firstName5.lLastName5@mail.com",new Location(5,"Città")));
        traineeList.add(new Trainee(6, "LastName6", "FirstName6",LocalDate.of(1994,5,5),"address5","Info","firstName6.lLastName6@mail.com",new Location(6,"Cidade")));
        traineeList.add(new Trainee(7, "LastName7", "FirstName7",LocalDate.of(1995,7,7),"address6","Info","firstName7.lLastName7@mail.com",new Location(7,"Extern")));
        traineeList.add(new Trainee(8, "LastName8", "FirstName8",LocalDate.of(1996,8,8),"address7","Info","firstName8.lLastName8.@mail.com",new Location(2,"Stadt")));
        traineeList.add(new Trainee(9, "LastName9", "FirstName9",LocalDate.of(1997,9,9),"address8","Info","firstName9.lLastName9.@mail.com",new Location(3,"Ville")));

        Set<Trainee> allTrainee =traineeDao.getAllTrainee();
        checkTraineeList(traineeList, allTrainee);
    }


    @Test
    public void insertTrainee() {
        boolean isInserted = traineeDao.insertTrainee(trainee10);
        Assert.assertTrue(isInserted);
        Trainee excepted = new Trainee(trainee10.getTraineeID(),trainee10.getLastName(),trainee10.getFirstName(),
                trainee10.getBirthday(),trainee10.getAddress(),trainee10.getSchool(),trainee10.getEmail(),trainee10.getLocation());
        Trainee attend = traineeDao.getTrainee(10);
        checkTrainee(attend, excepted);
        resetInsert();
    }


    public  void resetInsert() {
        TraineeDao traineeDao = new TraineeDaoImpl();
        traineeDao.deleteTrainee(trainee10.getTraineeID());
    }

    @Test
    public void updateTrainee() {
        trainee1.setLastName("test");
        boolean isUpdaded = traineeDao.updateTrainee(trainee1);
        Assert.assertTrue(isUpdaded);
        Trainee excepted = new Trainee(trainee1.getTraineeID(),"test",trainee1.getFirstName(),
                trainee1.getBirthday(),trainee1.getAddress(),trainee1.getSchool(),trainee1.getEmail(),trainee1.getLocation());
        checkTrainee(trainee1, excepted);
        resetUpdate();

    }

    private void resetUpdate() {
        trainee1.setLastName("LastName2");

    }

}