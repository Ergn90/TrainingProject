package model.location;

import java.util.Set;

public interface LocationDao {
    Location getLocation(int id);
    Set<Location> getAllLocation();
    boolean insertLocation(Location location);
    boolean updateLocation(Location courses);
    boolean deleteLocation(int id);
}
