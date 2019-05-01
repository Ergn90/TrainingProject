package tests;

import model.course.Course;
import model.course.CourseDaoImpl;
import model.location.Location;
import model.location.LocationDao;
import model.location.LocationDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class LocationDaoImplTest {


    private LocationDao locationDao;
    private Location location0;
    private Location location1;
    private Location location2;
    private Location location3;
    private Location location4;
    private Location location5;
    private Location location6;

    @Before
    public void preparedTest() {
        locationDao = new LocationDaoImpl();
        location0=new Location(1,"City");
        location1=new Location(2,"Stadt");
        location2=new Location(3,"Ville");
        location3=new Location(4,"Ciudad");
        location4=new Location(5,"Città");
        location5=new Location(6,"Cidade");
        location6=new Location(7,"Extern");

    }
    @Test
    public void getLocation() {
        Location location = locationDao.getLocation(1);
        checkLocation(location0,location);
    }

    private void checkLocation(Location excepted, Location attend) {
        Assert.assertEquals(excepted.getLocationId(),attend.getLocationId());
        Assert.assertEquals(excepted.getLocationName(),attend.getLocationName());
    }

    @Test
    public void getAllLocation() {
        Set<Location> allLocation = locationDao.getAllLocation();
        List<Location> locationList=new ArrayList<>();
        locationList.add(location0);
        locationList.add(location1);
        locationList.add(location2);
        locationList.add(location3);
        locationList.add(location4);
        locationList.add(location5);
        locationList.add(location6);
        checkLocationSet(locationList,allLocation);


    }

    private void checkLocationSet(List<Location> locations, Set<Location> atttend) {
        Assert.assertEquals(locations.size(), atttend.size());

        for (Iterator<Location> it = atttend.iterator(); it.hasNext(); ) {
            Location f = it.next();
            checkLocation(locations.get(f.getLocationId() - 1), f);
        }

    }


}