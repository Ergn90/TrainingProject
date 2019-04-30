package model.trainee;

import model.ConnectionFactory;
import model.location.Location;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TrainexeDaoImpl implements TraineeDao {

    private Trainee extractTraineeFromResultSet(ResultSet rs) throws SQLException {
        Trainee trainee = new Trainee();
        trainee.setTraineeID(rs.getInt("traineeID"));
        trainee.setLastName(rs.getString("lastName"));
        trainee.setFirstName(rs.getString("firstName"));
        trainee.setBirthday(rs.getDate("birthday").toLocalDate());
        trainee.setAddress(rs.getString("address"));
        trainee.setSchool(rs.getString("school"));
        trainee.setEmail(rs.getString("email"));
        Location location = new Location();
        location.setLocationId(rs.getInt("location.locationId"));
        location.setLocationName(rs.getString("LocationName"));
        trainee.setLocation(location);
        return trainee;
    }

    @Override
    public Trainee getTrainee(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from trainee inner join location on trainee.LocationId=location.LocationIdWHERE traineeID=" + id);

            if (rs.next()) {
                return extractTraineeFromResultSet(rs);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Trainee> getAllTrainee() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM trainee inner join location on trainee.LocationId=location.LocationId");
            Set trainees = new HashSet();
            while (rs.next()) {
                Trainee trainee = extractTraineeFromResultSet(rs);
                trainees.add(trainee);
            }
            return trainees;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertTrainee(Trainee trainee) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO trainee VALUES (NULL,?,?,?,?,?,?,?)");
            ps.setString(1, trainee.getLastName());
            ps.setString(2, trainee.getFirstName());
            ps.setDate(3,Date.valueOf(trainee.getBirthday()));
            ps.setString(4, trainee.getAddress());
            ps.setString(5, trainee.getSchool());
            ps.setString(6, trainee.getEmail());
            ps.setInt(7, trainee.getLocation().getLocationId());

            int i = ps.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTrainee(Trainee trainee) {
        Connection connection = ConnectionFactory.getConnection();
        try {

            PreparedStatement ps = connection.prepareStatement("UPDATE trainee SET lastName=?, firstName=?, " +
                    "birthday=?, address=?, school=?, email=?, locationId=?" +
                    " WHERE traineeId=?");

            ps.setString(1, trainee.getLastName());
            ps.setString(2, trainee.getFirstName());
            ps.setDate(3, Date.valueOf(trainee.getBirthday()));
            ps.setString(4, trainee.getAddress());
            ps.setString(5, trainee.getSchool());
            ps.setString(6, trainee.getEmail());
            ps.setInt(7, trainee.getLocation().getLocationId());
            ps.setInt(8, trainee.getTraineeID());

            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean deleteTrainee(int id) {

        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM trainee WHERE traineeID=" + id);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
