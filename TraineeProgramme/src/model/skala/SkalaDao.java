package model.skala;

import java.util.Set;

public interface SkalaDao {
    Skala getSkala(int id);
    Set<Skala> getAllSkala();
    boolean insertSkala(Skala skala);
    boolean updateSkala(Skala skala);
    boolean deleteSkala(int id);
}
