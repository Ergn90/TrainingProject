package model.location;

import model.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class LocationDaoImpl implements LocationDao {



    //TODO anderer select?
    @Override
    public Location getLocation(int id) {

        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement query = connection.createStatement();
            ResultSet loc = query.executeQuery("Select * from location where locationid=" + id);

            if (loc.next()) {
                return extractLocationFromSet(loc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Location extractLocationFromSet(ResultSet loc) throws SQLException {

        Location location = new Location();
        location.setLocationId(loc.getInt("LocationId"));
        location.setLocationName(loc.getString("LocationName"));
        return location;

    }

    @Override
    public Set<Location> getAllLocation() {

        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement query = connection.createStatement();
            ResultSet loc = query.executeQuery("select * from location");

            Set<Location> locations = new HashSet<Location>();

            while (loc.next()) {

                Location singleLocation = extractLocationFromSet(loc);
                locations.add(singleLocation);

            }

            return locations;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    //TODO evtl prüfung ob Location bereits vorhanden

    @Override
    public boolean insertLocation(Location location) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("insert into location (locationname) values(?)");
            statement.setString(1 ,location.getLocationName());

            int i = statement.executeUpdate();

            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateLocation(Location location) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("Update location set locationname = ? where locationid = ?");
            statement.setString(1, location.getLocationName());
            statement.setInt(2, location.getLocationId());

            int i = statement.executeUpdate();

            if (i == 1){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteLocation(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement stmt = null;

        try {

            stmt = connection.createStatement();
            int i = stmt.executeUpdate("delete from location where locationid ="+id);

            if(i == 1){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
