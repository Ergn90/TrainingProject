package tests;

import model.location.LocationDao;
import model.skala.Skala;
import model.skala.SkalaDao;
import model.skala.SkalaDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SkalaDaoImplTest {

    private SkalaDao skalaDao;
    private Skala skala0;
    private Skala skala1;
    private Skala skala2;
    private Skala skala3;


    @Test
    public void getSkala() {
        Skala skala = skalaDao.getSkala(1);
        checkSkala(skala0,skala);
    }

    @Test
    public void getAllSkala() {
        Set<Skala> allLocation = skalaDao.getAllSkala();
        List<Skala> skalaList=new ArrayList<>();
        skalaList.add(skala0);
        skalaList.add(skala1);
        skalaList.add(skala2);
        skalaList.add(skala3);

        checkSkalaSet(skalaList,allLocation);
    }


    @Before
    public void preparedTest() {
        skalaDao = new SkalaDaoImpl();
        skala0=new Skala(1,"Beginner");
        skala1=new Skala(2,"Intermediate");
        skala2=new Skala(3,"Advanced");
        skala3=new Skala(4,"Expert");


    }


    private void checkSkala(Skala excepted, Skala attend) {
        Assert.assertEquals(excepted.getSkalaId(),attend.getSkalaId());
        Assert.assertEquals(excepted.getSkalaName(),attend.getSkalaName());
    }



    private void checkSkalaSet(List<Skala> skalas, Set<Skala> atttend) {
        Assert.assertEquals(skalas.size(), atttend.size());

        for (Iterator<Skala> it = atttend.iterator(); it.hasNext(); ) {
            Skala f = it.next();
            checkSkala(skalas.get(f.getSkalaId() - 1), f);
        }

    }





}